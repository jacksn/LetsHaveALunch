package by.jackson.letshavealunch;

import by.jackson.letshavealunch.matcher.ModelMatcher;
import by.jackson.letshavealunch.model.Vote;
import by.jackson.letshavealunch.to.VoteTo;

import java.time.Month;
import java.util.Objects;

import static by.jackson.letshavealunch.UserTestData.USER;
import static by.jackson.letshavealunch.model.BaseEntity.START_SEQ;
import static java.time.LocalDate.of;

public final class VoteTestData {

    public static final ModelMatcher<Vote> MATCHER = ModelMatcher.of(Vote.class,
            (expected, actual) -> expected == actual
                    || (
                            Objects.equals(expected.getDate(), actual.getDate())
                            && Objects.equals(expected.getUser(), actual.getUser())
                            && Objects.equals(expected.getRestaurant(), actual.getRestaurant())
                    )
            );
    public static final ModelMatcher<VoteTo> MATCHER_VOTE_TO = ModelMatcher.of(VoteTo.class,
            (expected, actual) -> expected == actual
                    || (
                            Objects.equals(expected.getRestaurant(), actual.getRestaurant())
                            && Objects.equals(expected.getVoteCount(), actual.getVoteCount())
                            && Objects.equals(expected.isUserVote(), actual.isUserVote())
                    )
    );

    public static final int USER_VOTE1_ID  = START_SEQ + 16;

    public static final Vote VOTE1 = new Vote(
            USER_VOTE1_ID, USER,  RestaurantTestData.RESTAURANT1, of(2017, Month.FEBRUARY, 1));

    public static final VoteTo VOTE_TO_USER_1 = new VoteTo(RestaurantTestData.RESTAURANT1, 1, false);
    public static final VoteTo VOTE_TO_ADMIN_1 = new VoteTo(RestaurantTestData.RESTAURANT2, 1, true);

    private VoteTestData() {
    }
}
