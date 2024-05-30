package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema extends BaseSchema<Integer> {
    public NumberSchema required() {
        addCheck("required", Objects::nonNull);
        return this;
    }

    public NumberSchema positive() {
        addCheck("positive", number -> number == null || number > 0);
        return this;
    }

    public NumberSchema range(Integer num1, Integer num2) {
        addCheck("range", number -> number >= num1 && number <= num2);
        return this;
    }
}
