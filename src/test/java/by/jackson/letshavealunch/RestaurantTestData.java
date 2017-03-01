package by.jackson.letshavealunch;

import by.jackson.letshavealunch.matcher.ModelMatcher;
import by.jackson.letshavealunch.model.Restaurant;

import java.util.Objects;

import static by.jackson.letshavealunch.model.BaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final ModelMatcher<Restaurant> MATCHER = ModelMatcher.of(Restaurant.class,
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getName(), actual.getName())
                    )
    );

    public static final int RESTAURANT1_ID = START_SEQ + 2;
    public static final int RESTAURANT2_ID = START_SEQ + 3;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Charley G`s Seafood Grill");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT2_ID, "Cibo`s Bistro & Pizzeria");
}
