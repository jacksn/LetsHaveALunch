package by.jackson.letshavealunch.service;

import by.jackson.letshavealunch.DishTestData;
import by.jackson.letshavealunch.RestaurantTestData;
import by.jackson.letshavealunch.model.Menu;
import by.jackson.letshavealunch.model.MenuItem;
import by.jackson.letshavealunch.util.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static by.jackson.letshavealunch.MenuTestData.*;

public class MenuServiceTest extends AbstractServiceTest {

    @Autowired
    private MenuServiceImpl service;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
//        service.evictCache();
    }

    @Test
    public void testSave() throws Exception {
        LocalDate date = LocalDate.parse("2017-02-03");
        Menu created = new Menu(
                date,
                RestaurantTestData.RESTAURANT2,
                Arrays.asList(
                        new MenuItem(DishTestData.DISH2, 2.15F),
                        new MenuItem(DishTestData.DISH6, 4.35F)
                ));
        service.save(created);

        MATCHER.assertCollectionEquals(
                Collections.singletonList(created), service.getByDate(date));
    }

    @Test(expected = DataAccessException.class)
    public void testDuplicateSave() throws Exception {
        Menu duplicate = new Menu(
                LocalDate.parse("2017-02-01"),
                RestaurantTestData.RESTAURANT2,
                Arrays.asList(
                        new MenuItem(DishTestData.DISH3, 13F),
                        new MenuItem(DishTestData.DISH4, 8.86F)
                ));
        service.save(duplicate);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(MENU1_ID);
        MATCHER.assertCollectionEquals(
                Collections.singletonList(MENU2), service.getByDate(MENU1.getDate()));
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(1);
    }

    @Test
    public void testGet() throws Exception {
        Menu menu = service.get(MENU1_ID);
        MATCHER.assertEquals(MENU1, menu);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void testGetByDate() throws Exception {
        MATCHER.assertCollectionEquals(
                Arrays.asList(MENU1, MENU2), service.getByDate(MENU1.getDate()));
    }

    @Test
    public void testGetByDateAndRestaurant() throws Exception {
        MATCHER.assertCollectionEquals(
                Collections.singletonList(MENU1), service.getByDateAndRestaurant(MENU1.getDate(), MENU1.getRestaurant().getId()));
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(
                Arrays.asList(MENU3, MENU4, MENU1, MENU2), service.getAll());
    }

    @Test
    public void testUpdate() throws Exception {
        Menu updated = new Menu(MENU1_ID,
                LocalDate.parse("2017-02-01"),
                RestaurantTestData.RESTAURANT1,
                Arrays.asList(
                        new MenuItem(DishTestData.DISH1, 9.77F),
                        new MenuItem(DishTestData.DISH2, 8.85F),
                        new MenuItem(DishTestData.DISH8, 10.55F)
                ));
        service.update(updated);
        MATCHER.assertEquals(updated, service.get(MENU1_ID));
    }

    @Test
    public void testValidation() throws Exception {
        validateRootCause(() -> service.save(
                new Menu(MENU1_ID,
                        null,
                        RestaurantTestData.RESTAURANT1,
                        Arrays.asList(
                                new MenuItem(DishTestData.DISH1, 9.77F),
                                new MenuItem(DishTestData.DISH2, 8.85F),
                                new MenuItem(DishTestData.DISH8, 10.55F)
                        ))
        ), ConstraintViolationException.class);
        validateRootCause(() -> service.save(
                new Menu(MENU1_ID,
                        LocalDate.parse("2017-02-01"),
                        null,
                        Arrays.asList(
                                new MenuItem(DishTestData.DISH1, 9.77F),
                                new MenuItem(DishTestData.DISH2, 8.85F),
                                new MenuItem(DishTestData.DISH8, 10.55F)
                        ))
        ), ConstraintViolationException.class);
        validateRootCause(() -> service.save(
                new Menu(MENU1_ID,
                        LocalDate.parse("2017-02-01"),
                        RestaurantTestData.RESTAURANT1,
                        null)
        ), ConstraintViolationException.class);
    }
}