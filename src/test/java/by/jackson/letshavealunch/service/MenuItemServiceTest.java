package by.jackson.letshavealunch.service;

import by.jackson.letshavealunch.MenuItemTestData;
import by.jackson.letshavealunch.model.MenuItem;
import by.jackson.letshavealunch.util.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.Arrays;

import static by.jackson.letshavealunch.DishTestData.DISH3;
import static by.jackson.letshavealunch.DishTestData.DISH5;
import static by.jackson.letshavealunch.MenuItemTestData.*;
import static by.jackson.letshavealunch.RestaurantTestData.*;

public class MenuItemServiceTest extends AbstractServiceTest {

    @Autowired
    private MenuItemServiceImpl service;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
//        service.evictCache();
    }

    @Test
    public void testSave() throws Exception {
        MenuItem newMenuItem = new MenuItem(RESTAURANT1, DISH5, 15.10F, MENUITEM1.getDate());
        service.save(newMenuItem);
        MenuItemTestData.MATCHER.assertCollectionEquals(
                Arrays.asList(MENUITEM1, MENUITEM2, newMenuItem), service.getByDate(RESTAURANT1_ID, MENUITEM1.getDate()));
    }

    @Test(expected = DataAccessException.class)
    public void testDuplicateSave() throws Exception {
        MenuItem duplicate = new MenuItem(
                null,
                MENUITEM1.getRestaurant(),
                MENUITEM1.getDish(),
                MENUITEM1.getPrice(),
                MENUITEM1.getDate());
        service.save(duplicate);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(MENUITEM1_ID);
        MenuItemTestData.MATCHER.assertCollectionEquals(
                Arrays.asList(MENUITEM2), service.getByDate(RESTAURANT1_ID, MENUITEM1.getDate()));
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(1);
    }

    @Test
    public void testGet() throws Exception {
        MenuItem menuItem = service.get(MENUITEM1_ID);
        MenuItemTestData.MATCHER.assertEquals(MENUITEM1, menuItem);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void testGetByDate() throws Exception {
        MenuItemTestData.MATCHER.assertCollectionEquals(
                Arrays.asList(MENUITEM3, MENUITEM4), service.getByDate(RESTAURANT2_ID, MENUITEM1.getDate()));
    }

    @Test
    public void testGetAll() throws Exception {
        MenuItemTestData.MATCHER.assertCollectionEquals(
                Arrays.asList(MENUITEM5, MENUITEM6, MENUITEM1, MENUITEM2), service.getAll(RESTAURANT1_ID));
    }

    @Test
    public void testUpdate() throws Exception {
        MenuItem updated = new MenuItem(MENUITEM1_ID, MENUITEM1.getRestaurant(), DISH5, MENUITEM1.getPrice(), MENUITEM1.getDate());
        service.update(updated);
        MenuItemTestData.MATCHER.assertEquals(updated, service.get(MENUITEM1_ID));
    }

    @Test
    public void testValidation() throws Exception {
        validateRootCause(() -> service.save(new MenuItem(null, RESTAURANT1, DISH3, -1F, LocalDate.now())), ConstraintViolationException.class);
        validateRootCause(() -> service.save(new MenuItem(null, null, DISH3, 1F, LocalDate.now())), ConstraintViolationException.class);
        validateRootCause(() -> service.save(new MenuItem(null, RESTAURANT1, null, 1F, LocalDate.now())), ConstraintViolationException.class);
        validateRootCause(() -> service.save(new MenuItem(null, RESTAURANT1, DISH3, 1F, null)), ConstraintViolationException.class);
    }
}