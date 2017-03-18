package by.jackson.letshavealunch;

import by.jackson.letshavealunch.matcher.ModelMatcher;
import by.jackson.letshavealunch.model.Menu;
import by.jackson.letshavealunch.model.MenuItem;

import java.time.LocalDate;
import java.util.Arrays;

import static by.jackson.letshavealunch.model.BaseEntity.START_SEQ;

public class MenuTestData {
    public static final ModelMatcher<Menu> MATCHER = ModelMatcher.of(Menu.class);

    public static final int MENU1_ID = START_SEQ + 12;
    public static final int MENU2_ID = START_SEQ + 13;
    public static final int MENU3_ID = START_SEQ + 14;
    public static final int MENU4_ID = START_SEQ + 15;

    public static final Menu MENU1 = new Menu(MENU1_ID,
            LocalDate.parse("2017-02-01"),
            RestaurantTestData.RESTAURANT1,
            Arrays.asList(
                    new MenuItem(DishTestData.DISH1, 9.77F),
                    new MenuItem(DishTestData.DISH2, 8.85F)
            )); // 100012

    public static final Menu MENU2 = new Menu(MENU2_ID,
            LocalDate.parse("2017-02-01"),
            RestaurantTestData.RESTAURANT2,
            Arrays.asList(
                    new MenuItem(DishTestData.DISH3, 13F),
                    new MenuItem(DishTestData.DISH4, 8.86F)
            )); // 100013

    public static final Menu MENU3 = new Menu(MENU3_ID,
            LocalDate.parse("2017-02-02"),
            RestaurantTestData.RESTAURANT1,
            Arrays.asList(
                    new MenuItem(DishTestData.DISH5, 9.40F),
                    new MenuItem(DishTestData.DISH6, 6.13F)
            )); // 100014

    public static final Menu MENU4 = new Menu(MENU4_ID,
            LocalDate.parse("2017-02-02"),
            RestaurantTestData.RESTAURANT2,
            Arrays.asList(
                    new MenuItem(DishTestData.DISH7, 6.15F),
                    new MenuItem(DishTestData.DISH8, 8.35F)
            )); // 100015
}
