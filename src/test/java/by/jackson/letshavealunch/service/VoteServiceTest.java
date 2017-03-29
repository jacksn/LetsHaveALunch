package by.jackson.letshavealunch.service;

import by.jackson.letshavealunch.RestaurantTestData;
import by.jackson.letshavealunch.model.Vote;
import by.jackson.letshavealunch.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.HashSet;

import static by.jackson.letshavealunch.UserTestData.ADMIN_ID;
import static by.jackson.letshavealunch.UserTestData.USER;
import static by.jackson.letshavealunch.UserTestData.USER_ID;
import static by.jackson.letshavealunch.VoteTestData.*;
import static org.junit.Assume.assumeTrue;

public class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    protected VoteService service;

    @Test
    public void testDelete() throws Exception {
        service.delete(VOTE1.getDate(), USER_ID);
        MATCHER.assertEquals(null, service.getByDateForUser(VOTE1.getDate(), USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        service.delete(LocalDate.now(), USER_ID);
    }

    @Test
    public void testSave() throws Exception {
        LocalDate date = LocalDate.now();
        Vote created = new Vote(USER, RestaurantTestData.RESTAURANT1, date);
        service.save(RestaurantTestData.RESTAURANT1_ID, USER_ID);
        MATCHER.assertEquals(created, service.getByDateForUser(date, USER_ID));
    }

    @Test
    public void testUpdate() throws Exception {
        assumeTrue(LocalTime.now().getHour() < 11);
        LocalDate date = LocalDate.now();
        Vote updated = new Vote(USER_VOTE1_ID, USER, RestaurantTestData.RESTAURANT2, date);
        service.save(RestaurantTestData.RESTAURANT1_ID, USER_ID);
        service.save(RestaurantTestData.RESTAURANT2_ID, USER_ID);
        MATCHER.assertEquals(updated, service.getByDateForUser(date, USER_ID));
    }

    @Test
    public void testGetByDateForUser() throws Exception {
        MATCHER.assertEquals(VOTE1, service.getByDateForUser(LocalDate.of(2017, Month.FEBRUARY, 1), USER_ID));
    }

    @Test
    public void testGetByDate() throws Exception {
        MATCHER_VOTE_TO.assertCollectionEquals(new HashSet<>(Arrays.asList(VOTE_TO_ADMIN_1, VOTE_TO_USER_1)),
                service.getByDate(VOTE1.getDate(), ADMIN_ID));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveInvalid() throws Exception {
        service.save(null, USER_ID);
    }
}
