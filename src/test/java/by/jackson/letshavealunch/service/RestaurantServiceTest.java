package by.jackson.letshavealunch.service;

import by.jackson.letshavealunch.model.Dish;
import by.jackson.letshavealunch.model.Restaurant;
import by.jackson.letshavealunch.util.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import static by.jackson.letshavealunch.RestaurantTestData.*;

public class RestaurantServiceTest extends AbstractServiceTest {
    @Autowired
    private RestaurantService service;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        service.evictCache();
    }

    @Test
    public void testSave() throws Exception {
        Restaurant newRestaurant = new Restaurant("New restaurant", new HashSet<>(Arrays.asList(
                new Dish("New Dish 1", new BigDecimal("10.20")),
                new Dish("New Dish 2", new BigDecimal("3.35"))
        )));
        Restaurant created = service.save(newRestaurant);
        newRestaurant.setId(created.getId());
        MATCHER.assertCollectionEquals(
                Arrays.asList(RESTAURANT1, RESTAURANT2, created),
                service.getAll());
    }

    @Test(expected = DataAccessException.class)
    public void testDuplicateSave() throws Exception {
        service.save(new Restaurant(RESTAURANT1.getName(), new HashSet<>(Arrays.asList(
                new Dish("New Dish 1", new BigDecimal("10.20")),
                new Dish("New Dish 2", new BigDecimal("3.35"))
        ))));
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(RESTAURANT1_ID);
        MATCHER.assertCollectionEquals(Collections.singletonList(RESTAURANT2),
                service.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(1);
    }

    @Test
    public void testGet() throws Exception {
        Restaurant restaurant = service.get(RESTAURANT1_ID);
        MATCHER.assertEquals(RESTAURANT1, restaurant);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<Restaurant> all = service.getAll();
        MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT1, RESTAURANT2), all);
    }

    @Test
    public void testUpdate() throws Exception {
        Restaurant updated = new Restaurant(RESTAURANT1_ID, "UpdatedName", RESTAURANT1_DISHES);
        service.update(updated);
        MATCHER.assertEquals(updated, service.get(RESTAURANT1_ID));
    }

    @Test
    public void testValidation() throws Exception {
        validateRootCause(() -> service.save(new Restaurant("  ", RESTAURANT1_DISHES)), ConstraintViolationException.class);
        validateRootCause(() -> service.save(new Restaurant("New Restaurant", null)), ConstraintViolationException.class);
    }
}
