package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class NumberSchemaTest {

    Validator validator = new Validator();
    NumberSchema schema = validator.number();

    @ParameterizedTest
    @CsvSource(value = {"5, true", ", false"})
    void requiredIsUsedTest(Integer number, boolean expected) {
        schema.required();

        boolean result = schema.isValid(number);
        Assertions.assertEquals(result, expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"5, true", ", true"})
    void requiredIsNotUsedTest(Integer number, boolean expected) {
        boolean result = schema.isValid(number);
        Assertions.assertEquals(result, expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"5, true", "0, false", "-1, false"})
    void positiveTest(Integer number, boolean expected) {
        schema.positive();

        boolean result = schema.isValid(number);
        Assertions.assertEquals(result, expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"5, true", "10, true", "4, false", "11, false"})
    void rangeTest(Integer number, boolean expected) {
        final int start = 5;
        final int end = 10;
        schema.range(start, end);

        boolean result = schema.isValid(number);
        Assertions.assertEquals(result, expected);
    }
}
