package by.jackson.letshavealunch.service;

import by.jackson.letshavealunch.model.Dish;
import by.jackson.letshavealunch.util.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;

import static by.jackson.letshavealunch.DishTestData.*;

public class DishServiceTest extends AbstractServiceTest {
    @Autowired
    private DishService service;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        service.evictCache();
    }

    @Test
    public void testSave() throws Exception {
        Dish newDish = new Dish(null, "1 New dish");
        Dish created = service.save(newDish);
        newDish.setId(created.getId());
        MATCHER.assertCollectionEquals(
                Arrays.asList(newDish, DISH1, DISH2, DISH3, DISH4, DISH5, DISH6, DISH7, DISH8),
                service.getAll());
    }

    @Test(expected = DataAccessException.class)
    public void testDuplicateMailSave() throws Exception {
        service.save(new Dish(DISH1.getName()));
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(DISH1_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(DISH2, DISH3, DISH4, DISH5, DISH6, DISH7, DISH8),
                service.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(1);
    }

    @Test
    public void testGet() throws Exception {
        Dish dish = service.get(DISH1_ID);
        MATCHER.assertEquals(DISH1, dish);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(DISH1, DISH2, DISH3, DISH4, DISH5, DISH6, DISH7, DISH8), service.getAll());
    }

    @Test
    public void testUpdate() throws Exception {
        Dish updated = new Dish(DISH1.getId(), "UpdatedName");
        service.update(updated);
        MATCHER.assertEquals(updated, service.get(DISH1_ID));
    }

    @Test
    public void testValidation() throws Exception {
        validateRootCause(() -> service.save(new Dish("  ")), ConstraintViolationException.class);
    }
}
