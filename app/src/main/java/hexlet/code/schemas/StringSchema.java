package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema<String>{

    private Predicate<String> isRequired = string -> true;
    private Predicate<String> isContained = string -> true;
    private Predicate<String> hasMinLength = string -> true;

    public StringSchema required() {
        isRequired = string -> string != null && !string.isEmpty();
        return this;
    }

    public StringSchema minLength(int length) {
        hasMinLength = string -> string.length() >= length;
        return this;
    }

    public StringSchema contains(String text) {
        isContained = string -> {
            if (string == null) {
                return false;
            }
            return string.contains(text);
        };
        return this;
    }

    @Override
    public boolean isValid(String inputText) {
        return isRequired.test(inputText) && isContained.test(inputText) &&
                hasMinLength.test(inputText);
    }
}
