package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected Map<String, Predicate<T>> checks = new HashMap<>();

    protected final void addCheck(String name, Predicate<T> check) {
        checks.put(name, check);
    }

    public boolean isValid(T input) {
        return checks.values().stream().allMatch(check -> check.test(input));
    }
}
