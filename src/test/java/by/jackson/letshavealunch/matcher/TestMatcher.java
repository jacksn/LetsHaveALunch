package by.jackson.letshavealunch.matcher;

import by.jackson.letshavealunch.web.json.JsonUtil;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

/**
 * GKislin
 * 05.01.2015.
 */
public abstract class TestMatcher<T> extends BaseMatcher<String> {
    protected T expected;

    public TestMatcher(T expected) {
        this.expected = expected;
    }

    @Override
    public boolean matches(Object actual) {
        return compare(expected, (String) actual);
    }

    protected abstract boolean compare(T expected, String actual);

    @Override
    public void describeTo(Description description) {
        description.appendText(JsonUtil.writeValue(expected));
    }
}
