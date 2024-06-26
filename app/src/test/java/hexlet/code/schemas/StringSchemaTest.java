package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StringSchemaTest {

    private final Validator validator = new Validator();
    private final StringSchema schema = validator.string();

    @ParameterizedTest
    @CsvSource(value = {"text, true", "'', false", ", false"})
    void requiredIsUsedTest(String input, boolean expected) {
        schema.required();

        boolean result = schema.isValid(input);
        Assertions.assertEquals(result, expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"text, true", "'', true", ", true"})
    void requiredIsNotUsedTest(String input, boolean expected) {
        boolean result = schema.isValid(input);
        Assertions.assertEquals(result, expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"txt, false", "text, true", "'some text', true"})
    void minLengthTest(String input, boolean expected) {
        schema.minLength(4);

        boolean result = schema.isValid(input);
        Assertions.assertEquals(result, expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"'', false", ", false", "none, true", "two, false"})
    void containsTest(String input, boolean expected) {
        schema.contains("one");

        boolean result = schema.isValid(input);
        Assertions.assertEquals(result, expected);
    }
}
