package by.jackson.letshavealunch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import by.jackson.letshavealunch.matcher.ModelMatcher;
import by.jackson.letshavealunch.model.Role;
import by.jackson.letshavealunch.model.User;
import by.jackson.letshavealunch.util.PasswordUtil;

import java.util.Objects;

import static by.jackson.letshavealunch.model.BaseEntity.START_SEQ;

public final class UserTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "password", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ROLE_ADMIN, Role.ROLE_USER);
    private static final Logger LOG = LoggerFactory.getLogger(UserTestData.class);
    public static final ModelMatcher<User> MATCHER = ModelMatcher.of(User.class,
            (expected, actual) -> expected == actual
                    || (
                    comparePassword(expected.getPassword(), actual.getPassword())
                            && Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getName(), actual.getName())
                            && Objects.equals(expected.getEmail(), actual.getEmail())
                            && Objects.equals(expected.getRoles(), actual.getRoles())
                    )
    );

    private UserTestData() {
    }

    private static boolean comparePassword(String rawOrEncodedPassword, String password) {
        if (PasswordUtil.isEncoded(rawOrEncodedPassword)) {
            return rawOrEncodedPassword.equals(password);
        } else if (!PasswordUtil.isMatch(rawOrEncodedPassword, password)) {
            LOG.error("Password " + password + " doesn't match encoded " + password);
            return false;
        }
        return true;
    }

}
