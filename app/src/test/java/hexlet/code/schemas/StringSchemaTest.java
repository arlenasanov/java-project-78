package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class StringSchemaTest {

    Validator validator = new Validator();
    StringSchema schema = validator.string();

    @BeforeEach
    void preparation() {

    }

    @ParameterizedTest
    @CsvSource(value = {
            "text, true",
            "'', false",
            ", false"
    })
    void requiredUsedTest(String input, boolean expected) {
        schema.required();

        boolean result = schema.isValid(input);
        Assertions.assertEquals(result, expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "text, true",
            "'', true",
            ", true"
    })
    void requiredNotUsedTest(String input, boolean expected) {
        boolean result = schema.isValid(input);
        Assertions.assertEquals(result, expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "txt, false",
            "text, true",
            "'some text', true"
    })
    void minLength(String input, boolean expected) {
        schema.minLength(4);

        boolean result = schema.isValid(input);
        Assertions.assertEquals(result, expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "'', false",
            ", false",
            "none, true",
            "two, false"
    })
    void contains(String input, boolean expected) {
        schema.contains("one");

        boolean result = schema.isValid(input);
        Assertions.assertEquals(result, expected);
    }
}