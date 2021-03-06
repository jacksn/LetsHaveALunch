package by.jackson.letshavealunch.web;

import by.jackson.letshavealunch.AppConfig;
import by.jackson.letshavealunch.RestaurantTestData;
import by.jackson.letshavealunch.model.Vote;
import by.jackson.letshavealunch.service.VoteService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

import static by.jackson.letshavealunch.TestUtil.userHttpBasic;
import static by.jackson.letshavealunch.UserTestData.*;
import static by.jackson.letshavealunch.VoteTestData.MATCHER;
import static by.jackson.letshavealunch.VoteTestData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VoteControllerTest extends AbstractControllerTest {

    private static final String REST_URL = VoteController.REST_URL + '/';

    @Autowired
    private VoteService service;

    @Autowired
    private AppConfig appConfig;

    @Test
    public void testGetByDate() throws Exception {
        mockMvc.perform(get(REST_URL + "?date=" + VOTE1.getDate())
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER_VOTE_TO.contentListMatcher(VOTE_TO_ADMIN_1, VOTE_TO_USER_1));
    }

    @Test
    public void testGetUnauth() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Transactional
    @Test
    public void testUpdateVote() throws Exception {
        appConfig.setVotingEndTime(LocalTime.MAX);
        mockMvc.perform(post(REST_URL + RestaurantTestData.RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isOk());
        mockMvc.perform(post(REST_URL + RestaurantTestData.RESTAURANT2_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Transactional
    @Test
    public void testUpdateVoteAfterVotingEnd() throws Exception {
        appConfig.setVotingEndTime(LocalTime.MIN);
        LocalDate date = LocalDate.now();
        Vote updated = new Vote(USER, RestaurantTestData.RESTAURANT2, date);
        mockMvc.perform(post(REST_URL + RestaurantTestData.RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(post(REST_URL + RestaurantTestData.RESTAURANT2_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @Transactional
    public void testVote() throws Exception {
        LocalDate date = LocalDate.now();
        Vote created = new Vote(USER, RestaurantTestData.RESTAURANT1, date);
        mockMvc.perform(post(REST_URL + RestaurantTestData.RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isOk());
        MATCHER.assertEquals(created, service.getByDateAndUserId(date, USER_ID));
    }

    @Test
    public void testVoteInvalid() throws Exception {
        mockMvc.perform(post(REST_URL + "1")
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @Transactional
    public void testDeleteVote() throws Exception {
        LocalDate date = LocalDate.now();
        mockMvc.perform(post(REST_URL + RestaurantTestData.RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isOk());
        mockMvc.perform(delete(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isOk());
        MATCHER.assertEquals(null, service.getByDateAndUserId(date, USER_ID));
    }
}
