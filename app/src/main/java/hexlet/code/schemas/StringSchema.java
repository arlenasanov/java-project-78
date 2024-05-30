package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {
    public StringSchema required() {
        addCheck("required", string -> string != null && !string.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        addCheck("minLength", string -> {
            if (string == null) {
                return false;
            }
            return string.length() >= length;
        });
        return this;
    }

    public StringSchema contains(String text) {
        addCheck("contains", string -> {
            if (string == null) {
                return false;
            }
            return string.contains(text);
        });
        return this;
    }
}
