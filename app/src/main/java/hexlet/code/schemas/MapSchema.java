package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    public MapSchema required() {
        checks.put("required", Objects::nonNull);
        return this;
    }

    public MapSchema sizeof(int size) {
        checks.put("required", map -> {
            if (map == null) {
                return false;
            }
            return map.size() == size;
        });
        return this;
    }

    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        checks.put("shape", map ->
                schemas.entrySet().stream().allMatch(entry -> entry.getValue().isValid((T) map.get(entry.getKey())))
        );
        return this;
    }
}
