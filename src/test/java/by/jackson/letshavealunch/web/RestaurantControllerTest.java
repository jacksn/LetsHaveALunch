package by.jackson.letshavealunch.web;

import by.jackson.letshavealunch.model.Restaurant;
import by.jackson.letshavealunch.service.RestaurantService;
import by.jackson.letshavealunch.web.json.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;

import static by.jackson.letshavealunch.RestaurantTestData.*;
import static by.jackson.letshavealunch.TestUtil.userHttpBasic;
import static by.jackson.letshavealunch.UserTestData.ADMIN;
import static by.jackson.letshavealunch.UserTestData.USER;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RestaurantControllerTest extends AbstractControllerTest {
    private static final String REST_URL = RestaurantController.REST_URL + '/';

    @Autowired
    private RestaurantService service;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT1_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(RESTAURANT1));
    }

//    @Test
//    public void testGetMenus() throws Exception {
//        mockMvc.perform(get(REST_URL + RESTAURANT1_ID + "/menus")
//                .with(userHttpBasic(USER)))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(MenuTestData.MATCHER.contentListMatcher(MENU3, MENU1));
//    }
//
//    @Test
//    public void testGetMenusByDate() throws Exception {
//        mockMvc.perform(get(REST_URL + RESTAURANT1_ID + "/menus?date=" + MENU1.getDate())
//                .with(userHttpBasic(USER)))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(MenuTestData.MATCHER.contentListMatcher(MENU1));
//    }

    @Test
    public void testGetUnauth() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT1_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + 1)
                .with(userHttpBasic(USER)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(REST_URL + 1)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @Transactional
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + RESTAURANT1_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk());
        MATCHER.assertCollectionEquals(Collections.singletonList(RESTAURANT2), service.getAll());
    }

    @Test
    @Transactional
    public void testDeleteDenied() throws Exception {
        mockMvc.perform(delete(REST_URL + RESTAURANT1_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isForbidden());
    }

    @Test
    @Transactional
    public void testUpdate() throws Exception {
        Restaurant updated = new Restaurant(RESTAURANT1_ID, "New name");

        mockMvc.perform(put(REST_URL + RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk());

        assertEquals(updated, service.get(RESTAURANT1_ID));
    }

    @Test
    public void testUpdateDenied() throws Exception {
        Restaurant updated = new Restaurant(RESTAURANT1_ID, "New name");

        mockMvc.perform(put(REST_URL + RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(USER)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testUpdateInvalid() throws Exception {
        Restaurant invalid = new Restaurant(RESTAURANT1_ID, "");
        mockMvc.perform(put(REST_URL + RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void testUpdateHtmlUnsafe() throws Exception {
        Restaurant invalid = new Restaurant(RESTAURANT1_ID, "<script>alert(123)</script>");
        mockMvc.perform(put(REST_URL + RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @Transactional
    public void testCreate() throws Exception {
        Restaurant newRestaurant = new Restaurant("New restaurant");
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newRestaurant))
                .with(userHttpBasic(ADMIN)));

        Restaurant returned = MATCHER.fromJsonAction(action);
        newRestaurant.setId(returned.getId());

        MATCHER.assertEquals(newRestaurant, returned);
        MATCHER.assertCollectionEquals(
                Arrays.asList(RESTAURANT1, RESTAURANT2, newRestaurant),
                service.getAll());
    }

    @Test
    public void testCreateInvalid() throws Exception {
        Restaurant invalid = new Restaurant("");
        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void testCreateDenied() throws Exception {
        Restaurant invalid = new Restaurant("New restaurant");
        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(USER)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(RESTAURANT1, RESTAURANT2));
    }

    @Test
    public void testUpdateDuplicate() throws Exception {
        Restaurant duplicate = new Restaurant(RESTAURANT2.getName());
        mockMvc.perform(put(REST_URL + RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(duplicate))
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    public void testCreateDuplicate() throws Exception {
        Restaurant invalid = new Restaurant(RESTAURANT2.getName());
        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isConflict());
    }
}
