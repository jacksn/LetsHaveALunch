package by.jackson.letshavealunch;

import by.jackson.letshavealunch.matcher.ModelMatcher;
import by.jackson.letshavealunch.model.MenuItem;

import java.time.LocalDate;
import java.util.Objects;

import static by.jackson.letshavealunch.model.BaseEntity.START_SEQ;

public class MenuItemTestData {
    public static final ModelMatcher<MenuItem> MATCHER = ModelMatcher.of(MenuItem.class,
            (expected, actual) -> expected == actual ||
                    (
                            Objects.equals(expected.getId(), actual.getId())
                                    && Objects.equals(expected.getDate(), actual.getDate())
                                    && Objects.equals(expected.getRestaurant(), actual.getRestaurant())
                                    && Objects.equals(expected.getDish(), actual.getDish())
                                    && Objects.equals(expected.getPrice(), actual.getPrice())
                    )
    );

    public static final int MENUITEM1_ID = START_SEQ + 12;
    public static final int MENUITEM2_ID = START_SEQ + 13;
    public static final int MENUITEM3_ID = START_SEQ + 14;
    public static final int MENUITEM4_ID = START_SEQ + 15;

    public static final int MENUITEM5_ID = START_SEQ + 16;
    public static final int MENUITEM6_ID = START_SEQ + 17;
    public static final int MENUITEM7_ID = START_SEQ + 18;
    public static final int MENUITEM8_ID = START_SEQ + 19;

    public static final MenuItem MENUITEM1 = new MenuItem(
            MENUITEM1_ID, RestaurantTestData.RESTAURANT1, DishTestData.DISH1, 9.77F, LocalDate.parse("2017-02-01")); // 100012
    public static final MenuItem MENUITEM2 = new MenuItem(
            MENUITEM2_ID, RestaurantTestData.RESTAURANT1, DishTestData.DISH2, 8.85F, LocalDate.parse("2017-02-01")); // 100005
    public static final MenuItem MENUITEM3 = new MenuItem(
            MENUITEM3_ID, RestaurantTestData.RESTAURANT2, DishTestData.DISH3, 13F, LocalDate.parse("2017-02-01")); // 100006
    public static final MenuItem MENUITEM4 = new MenuItem(
            MENUITEM4_ID, RestaurantTestData.RESTAURANT2, DishTestData.DISH4, 8.86F, LocalDate.parse("2017-02-01")); // 100007
    public static final MenuItem MENUITEM5 = new MenuItem(
            MENUITEM5_ID, RestaurantTestData.RESTAURANT1, DishTestData.DISH5, 9.40F, LocalDate.parse("2017-02-02")); // 100008
    public static final MenuItem MENUITEM6 = new MenuItem(
            MENUITEM6_ID, RestaurantTestData.RESTAURANT1, DishTestData.DISH6, 6.13F, LocalDate.parse("2017-02-02")); // 100009
    public static final MenuItem MENUITEM7 = new MenuItem(
            MENUITEM7_ID, RestaurantTestData.RESTAURANT2, DishTestData.DISH7, 6.15F, LocalDate.parse("2017-02-02")); // 100010
    public static final MenuItem MENUITEM8 = new MenuItem(
            MENUITEM8_ID, RestaurantTestData.RESTAURANT2, DishTestData.DISH8, 8.35F, LocalDate.parse("2017-02-02")); // 100011

}
