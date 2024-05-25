package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {
    public StringSchema required() {
        checks.put("required", string -> string != null && !string.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        checks.put("required", string -> {
            if (string == null) {
                return false;
            }
            return string.length() >= length;
        });
        return this;
    }

    public StringSchema contains(String text) {
        checks.put("required", string -> {
            if (string == null) {
                return false;
            }
            return string.contains(text);
        });
        return this;
    }
}
