package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MapSchemaTest {

    Validator validator = new Validator();
    MapSchema schema = validator.map();

    @Test
    void requiredIsUsedTest() {
        schema.required();

        Map<String, String> map1 = new HashMap<>();
        boolean expected = true;
        boolean result = schema.isValid(map1);
        assertEquals(expected, result);

        Map<String, String> map2 = null;
        expected = false;
        result = schema.isValid(map2);
        assertEquals(expected, result);

        Map<String, String> map3 = new HashMap<>();
        map3.put("ru", "+7");
        map3.put("kg", "+996");
        expected = true;
        result = schema.isValid(map3);
        assertEquals(expected, result);
    }

    @Test
    void sizeofTest() {
        schema.sizeof(1);

        Map<String, String> map = new HashMap<>();
        map.put("ru", "+7");
        map.put("kg", "+996");
        boolean expected = false;
        boolean result = schema.isValid(map);
        assertEquals(expected, result);

        schema.sizeof(2);
        expected = true;
        result = schema.isValid(map);
        assertEquals(expected, result);
    }

    @Test
    void shape() {
        Map<String, BaseSchema<String>> schemas1 = new HashMap<>();
        schemas1.put("firstName", validator.string().required());
        schemas1.put("lastName", validator.string().required().minLength(2));
        schema.shape(schemas1);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        boolean expected = true;
        boolean result = schema.isValid(human1);
        assertEquals(expected, result);

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        expected = false;
        result = schema.isValid(human2);
        assertEquals(expected, result);

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        expected = false;
        result = schema.isValid(human3);
        assertEquals(expected, result);

        Map<String, BaseSchema<Integer>> schemas2 = new HashMap<>();
        schemas2.put("gdp", validator.number().required());
        schemas2.put("population", validator.number().positive());
        schemas2.put("cities", validator.number().range(10, 100));
        schema.shape(schemas2);
        Map<String, Integer> country = new HashMap<>();
        country.put("gdp", 100000000);
        country.put("population", 500000);
        country.put("cities", 9);
        expected = false;
        result = schema.isValid(country);
        assertEquals(expected, result);
    }
}
