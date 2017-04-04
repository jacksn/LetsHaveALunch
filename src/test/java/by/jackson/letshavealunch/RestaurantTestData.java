package by.jackson.letshavealunch;

import by.jackson.letshavealunch.matcher.ModelMatcher;
import by.jackson.letshavealunch.model.Dish;
import by.jackson.letshavealunch.model.Restaurant;

import java.math.BigDecimal;
import java.util.*;

import static by.jackson.letshavealunch.model.BaseEntity.START_SEQ;

public final class RestaurantTestData {

    public static final ModelMatcher<Restaurant> MATCHER = ModelMatcher.of(Restaurant.class,
            (expected, actual) -> expected == actual
                    || (Objects.equals(expected.getId(), actual.getId())
                    && Objects.equals(expected.getName(), actual.getName())
                    && Objects.equals(new ArrayList<>(expected.getDishes()), new ArrayList<>(actual.getDishes())))
    );

    public static final int RESTAURANT1_ID = START_SEQ + 2;
    public static final int RESTAURANT2_ID = START_SEQ + 3;

    public static final Dish DISH1 = new Dish("Basted Paprika Pheasant", new BigDecimal("9.77"));
    public static final Dish DISH2 = new Dish("Breaded Sour & Cream Quail", new BigDecimal("8.85"));

    public static final Dish DISH3 = new Dish("Grilled Apricot & Basil Horse", new BigDecimal("13.00"));
    public static final Dish DISH4 = new Dish("Cooked Dark Beer Winter Greens", new BigDecimal("8.86"));

    public static final Dish DISH5 = new Dish("Marinated Fennel & Lemon Forest Mushrooms", new BigDecimal("9.40"));
    public static final Dish DISH6 = new Dish("Slow-Cooked Ginger Rabbit", new BigDecimal("6.13"));

    public static final Dish DISH7 = new Dish("Smoked Mint & Berry Crocodile", new BigDecimal("6.15"));
    public static final Dish DISH8 = new Dish("Thermal-Cooked Saffron & Shallot Salmon", new BigDecimal("8.35"));

    public static final Set<Dish> RESTAURANT1_DISHES = new HashSet<>(Arrays.asList(DISH1, DISH2, DISH3, DISH4));
    public static final Set<Dish> RESTAURANT2_DISHES = new HashSet<>(Arrays.asList(DISH5, DISH6, DISH7, DISH8));

    public static final Restaurant RESTAURANT1 =
            new Restaurant(RESTAURANT1_ID, "Charley G`s Seafood Grill", new HashSet<>(Arrays.asList(
                    DISH1, DISH2, DISH3, DISH4
            )));
    public static final Restaurant RESTAURANT2 =
            new Restaurant(RESTAURANT2_ID, "Cibo`s Bistro & Pizzeria",  new HashSet<>(Arrays.asList(
                    DISH5, DISH6, DISH7, DISH8
                    )));

    private RestaurantTestData() {
    }
}
