package by.jackson.letshavealunch.service;

import by.jackson.letshavealunch.RestaurantTestData;
import by.jackson.letshavealunch.model.Vote;
import by.jackson.letshavealunch.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;
import java.time.Month;
import java.util.Arrays;

import static by.jackson.letshavealunch.UserTestData.*;
import static by.jackson.letshavealunch.VoteTestData.*;
import static by.jackson.letshavealunch.VoteTestData.MATCHER;
import static java.time.LocalDate.of;

public class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    protected VoteService service;

    @Test
    public void testGet() throws Exception {
        Vote actual = service.get(ADMIN_VOTE1_ID, ADMIN_ID);
        MATCHER.assertEquals(ADMIN_VOTE1, actual);
    }

    @Test
    public void testGetNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(USER_VOTE1_ID, ADMIN_ID);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(USER_VOTE1_ID, USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(VOTE2), service.getAll(USER_ID));
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(USER_VOTE1_ID, ADMIN_ID);
    }

    @Test
    public void testSave() throws Exception {
        Vote created = getCreated();
        service.save(created, USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(created, VOTE2, VOTE1), service.getAll(USER_ID));
    }

    @Test
    public void testUpdate() throws Exception {
        Vote updated = getUpdated();
        service.update(updated, USER_ID);
        MATCHER.assertEquals(updated, service.get(USER_VOTE1_ID, USER_ID));
    }

    @Test
    public void testUpdateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + USER_VOTE1_ID);
        service.update(VOTE1, ADMIN_ID);
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(VOTE2, VOTE1), service.getAll(USER_ID));
    }

    @Test
    public void testGetByDate() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(VOTE1),
                service.getByDate(of(2017, Month.FEBRUARY, 1), USER_ID));
    }

//    @Test
//    public void testGetWithUser() throws Exception {
//        Vote adminVote = service.getWithUser(ADMIN_VOTE_ID, ADMIN_ID);
//        MATCHER.assertEquals(ADMIN_VOTE, adminVote);
//        UserTestData.MATCHER.assertEquals(UserTestData.ADMIN, adminVote.getUser());
//    }
//
//    @Test(expected = NotFoundException.class)
//    public void testGetWithUserNotFound() throws Exception {
//        service.getWithUser(USER_VOTE_ID, ADMIN_ID);
//    }
//
    @Test
    public void testValidation() throws Exception {
        validateRootCause(() -> service.save(new Vote(null, null, of(2015, Month.JUNE, 1)), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.save(new Vote(null, RestaurantTestData.RESTAURANT1, null), USER_ID), ConstraintViolationException.class);
    }
}
