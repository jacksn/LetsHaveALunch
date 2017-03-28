package by.jackson.letshavealunch.service;

import by.jackson.letshavealunch.UserTestData;
import by.jackson.letshavealunch.model.Role;
import by.jackson.letshavealunch.model.User;
import by.jackson.letshavealunch.util.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class UserServiceTest extends AbstractServiceTest {
    @Autowired
    private UserService service;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        service.evictCache();
    }

    @Test
    public void testSave() throws Exception {
        User newUser = new User(null, "New", "new@gmail.com", "newPass", Collections.singleton(Role.ROLE_USER));
        User created = service.save(newUser);
        newUser.setId(created.getId());
        UserTestData.MATCHER.assertCollectionEquals(Arrays.asList(UserTestData.ADMIN, newUser, UserTestData.USER), service.getAll());
    }

    @Test(expected = DataAccessException.class)
    public void testDuplicateMailSave() throws Exception {
        service.save(new User(null, "Duplicate", "user@yandex.ru", "newPass", Role.ROLE_USER));
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(UserTestData.USER_ID);
        UserTestData.MATCHER.assertCollectionEquals(Collections.singletonList(UserTestData.ADMIN), service.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(1);
    }

    @Test
    public void testGet() throws Exception {
        User user = service.get(UserTestData.ADMIN_ID);
        UserTestData.MATCHER.assertEquals(UserTestData.ADMIN, user);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void testGetByEmail() throws Exception {
        User user = service.getByEmail("admin@gmail.com");
        UserTestData.MATCHER.assertEquals(UserTestData.ADMIN, user);
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<User> all = service.getAll();
        UserTestData.MATCHER.assertCollectionEquals(Arrays.asList(UserTestData.ADMIN, UserTestData.USER), all);
    }

    @Test
    public void testUpdate() throws Exception {
        User updated = new User(UserTestData.USER);
        updated.setName("UpdatedName");
        updated.setRoles(Collections.singletonList(Role.ROLE_ADMIN));
        service.update(updated);
        UserTestData.MATCHER.assertEquals(updated, service.get(UserTestData.USER_ID));
    }

    @Test
    public void testValidation() throws Exception {
        validateRootCause(() -> service.save(new User(null, "  ", "invalid@yandex.ru", "password", Role.ROLE_USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.save(new User(null, "User", "  ", "password", Role.ROLE_USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.save(new User(null, "User", "invalid@yandex.ru", "  ", Role.ROLE_USER)), ConstraintViolationException.class);
    }
}
