package by.jackson.letshavealunch;

import by.jackson.letshavealunch.matcher.ModelMatcher;
import by.jackson.letshavealunch.model.Vote;

import java.time.LocalDate;
import java.time.Month;

import static by.jackson.letshavealunch.model.BaseEntity.START_SEQ;
import static java.time.LocalDate.of;

public class VoteTestData {

    public static final ModelMatcher<Vote> MATCHER = ModelMatcher.of(Vote.class);

    public static final int USER_VOTE1_ID  = START_SEQ + 20;
    public static final int ADMIN_VOTE1_ID = START_SEQ + 21;
    public static final int USER_VOTE2_ID  = START_SEQ + 22;
    public static final int ADMIN_VOTE2_ID = START_SEQ + 23;

    public static final Vote VOTE1 = new Vote(
            USER_VOTE1_ID,  RestaurantTestData.RESTAURANT1, of(2017, Month.FEBRUARY, 1));
    public static final Vote ADMIN_VOTE1 = new Vote(
            ADMIN_VOTE1_ID, RestaurantTestData.RESTAURANT2, of(2017, Month.FEBRUARY, 1));
    public static final Vote VOTE2 = new Vote(
            USER_VOTE2_ID,  RestaurantTestData.RESTAURANT2, of(2017, Month.FEBRUARY, 2));
    public static final Vote ADMIN_VOTE2 = new Vote(
            ADMIN_VOTE2_ID, RestaurantTestData.RESTAURANT2, of(2017, Month.FEBRUARY, 2));

    public static Vote getCreated() {
        return new Vote(null, RestaurantTestData.RESTAURANT1, LocalDate.now());
    }

    public static Vote getUpdated() {
        return new Vote(USER_VOTE1_ID, RestaurantTestData.RESTAURANT2, VOTE1.getDate());
    }
}
