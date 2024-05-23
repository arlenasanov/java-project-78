package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public class NumberSchema extends BaseSchema<Integer> {
    private Predicate<Integer> isRequired = number -> true;
    private Predicate<Integer> isPositive = number -> true;
    private Predicate<Integer> isInRange = number -> true;

    public NumberSchema required() {
        isRequired = Objects::nonNull;
        return this;
    }

    public NumberSchema positive() {
        isPositive = number -> number > 0;
        return this;
    }

    public NumberSchema range(Integer num1, Integer num2) {
        isInRange = number -> number >= num1 && number <= num2;
        return this;
    }

    @Override
    public boolean isValid(Integer inputText) {
        return isRequired.test(inputText) && isPositive.test(inputText) &&
                isInRange.test(inputText);
    }
}
