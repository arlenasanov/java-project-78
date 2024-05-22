package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema {

    private Predicate<String> isRequired = s -> true;
    private Predicate<String> isContained = s -> true;
    private Predicate<String> hasMinLength = s -> true;

    public StringSchema required() {
        isRequired = s -> s != null && !s.isEmpty();
        return this;
    }

    public StringSchema minLength(int length) {
        hasMinLength = s -> s.length() >= length;
        return this;
    }

    public StringSchema contains(String text) {
        isContained = s -> {
            if (s == null) {
                return false;
            }
            return s.contains(text);
        };
        return this;
    }

    public boolean isValid(String inputText) {
        return isRequired.test(inputText) && isContained.test(inputText) &&
                hasMinLength.test(inputText);
    }
}
