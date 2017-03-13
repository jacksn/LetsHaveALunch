package by.jackson.letshavealunch;

import by.jackson.letshavealunch.matcher.ModelMatcher;
import by.jackson.letshavealunch.model.Dish;

import java.util.Objects;

import static by.jackson.letshavealunch.model.BaseEntity.START_SEQ;

public class DishTestData {
    public static final ModelMatcher<Dish> MATCHER = ModelMatcher.of(Dish.class,
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getName(), actual.getName())
                    )
    );

    public static final int DISH1_ID = START_SEQ + 4;
    public static final int DISH2_ID = START_SEQ + 5;
    public static final int DISH3_ID = START_SEQ + 6;
    public static final int DISH4_ID = START_SEQ + 7;
    public static final int DISH5_ID = START_SEQ + 8;
    public static final int DISH6_ID = START_SEQ + 9;
    public static final int DISH7_ID = START_SEQ + 10;
    public static final int DISH8_ID = START_SEQ + 11;

    public static final Dish DISH1 = new Dish(DISH1_ID, "Grilled Apricot & Basil Horse");             // 100004
    public static final Dish DISH2 = new Dish(DISH2_ID, "Slow-Cooked Ginger Rabbit");                 // 100005
    public static final Dish DISH3 = new Dish(DISH3_ID, "Thermal-Cooked Saffron & Shallot Salmon");   // 100006
    public static final Dish DISH4 = new Dish(DISH4_ID, "Smoked Mint & Berry Crocodile");             // 100007
    public static final Dish DISH5 = new Dish(DISH5_ID, "Cooked Dark Beer Winter Greens");            // 100008
    public static final Dish DISH6 = new Dish(DISH6_ID, "Marinated Fennel & Lemon Forest Mushrooms"); // 100009
    public static final Dish DISH7 = new Dish(DISH7_ID, "Basted Paprika Pheasant");                   // 100010
    public static final Dish DISH8 = new Dish(DISH8_ID, "Breaded Sour & Cream Quail");                // 100011

}
