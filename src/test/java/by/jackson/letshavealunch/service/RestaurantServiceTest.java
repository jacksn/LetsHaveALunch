package by.jackson.letshavealunch.service;

import by.jackson.letshavealunch.model.Restaurant;
import by.jackson.letshavealunch.util.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.Collection;

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
        Restaurant newRestaurant = new Restaurant("New restaurant");
        Restaurant created = service.save(newRestaurant);
        newRestaurant.setId(created.getId());
        MATCHER.assertCollectionEquals(
                Arrays.asList(RESTAURANT1, RESTAURANT2, newRestaurant),
                service.getAll());
    }

    @Test(expected = DataAccessException.class)
    public void testDuplicateSave() throws Exception {
        service.save(new Restaurant(RESTAURANT1.getName()));
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(RESTAURANT1_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT2),
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
        Restaurant updated = new Restaurant(RESTAURANT1_ID, "UpdatedName");
        service.update(updated);
        MATCHER.assertEquals(updated, service.get(RESTAURANT1_ID));
    }

    @Test
    public void testValidation() throws Exception {
        validateRootCause(() -> service.save(new Restaurant("  ")), ConstraintViolationException.class);
    }
}
