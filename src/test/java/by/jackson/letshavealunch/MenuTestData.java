package by.jackson.letshavealunch;

import by.jackson.letshavealunch.matcher.ModelMatcher;
import by.jackson.letshavealunch.model.Menu;
import by.jackson.letshavealunch.model.MenuItem;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import static by.jackson.letshavealunch.model.BaseEntity.START_SEQ;

public final class MenuTestData {

    public static final ModelMatcher<Menu> MATCHER = ModelMatcher.of(Menu.class);

    public static final int MENU1_ID = START_SEQ + 12;
    public static final int MENU2_ID = START_SEQ + 13;
    public static final int MENU3_ID = START_SEQ + 14;
    public static final int MENU4_ID = START_SEQ + 15;

    public static final Menu MENU1 = new Menu(MENU1_ID,
            LocalDate.parse("2017-02-01"),
            RestaurantTestData.RESTAURANT1,
            Arrays.asList(
                    new MenuItem(DishTestData.DISH1, new BigDecimal("9.77")),
                    new MenuItem(DishTestData.DISH2, new BigDecimal("8.85"))
            )); // 100012

    public static final Menu MENU2 = new Menu(MENU2_ID,
            LocalDate.parse("2017-02-01"),
            RestaurantTestData.RESTAURANT2,
            Arrays.asList(
                    new MenuItem(DishTestData.DISH3, new BigDecimal("13.00")),
                    new MenuItem(DishTestData.DISH4, new BigDecimal("8.86"))
            )); // 100013

    public static final Menu MENU3 = new Menu(MENU3_ID,
            LocalDate.parse("2017-02-02"),
            RestaurantTestData.RESTAURANT1,
            Arrays.asList(
                    new MenuItem(DishTestData.DISH5, new BigDecimal("9.40")),
                    new MenuItem(DishTestData.DISH6, new BigDecimal("6.13"))
            )); // 100014

    public static final Menu MENU4 = new Menu(MENU4_ID,
            LocalDate.parse("2017-02-02"),
            RestaurantTestData.RESTAURANT2,
            Arrays.asList(
                    new MenuItem(DishTestData.DISH7, new BigDecimal("6.15")),
                    new MenuItem(DishTestData.DISH8, new BigDecimal("8.35"))
            )); // 100015

    private MenuTestData() {
    }
}
