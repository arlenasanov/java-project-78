package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public class NumberSchema extends BaseSchema<Integer> {
    public NumberSchema required() {
        checks.put("required", Objects::nonNull);
        return this;
    }

    public NumberSchema positive() {
        checks.put("positive", number -> number == null || number > 0);
        return this;
    }

    public NumberSchema range(Integer num1, Integer num2) {
        checks.put("positive",number -> number >= num1 && number <= num2);
        return this;
    }
}
