package hexlet.code.formatter;

import hexlet.code.utils.Wrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class JsonFormatterTest {
    private TreeMap<String, Wrapper> testMap;
    private String expected;

    JsonFormatterTest() {
    }

    @BeforeEach
    void setUp() {
        testMap = new TreeMap<>();
        testMap.put("testKey", new Wrapper("value1", "value2"));
        testMap.put("testKey1", new Wrapper("-absent-", "value21"));
        testMap.put("testKey2", new Wrapper("value12", "-absent-"));
        testMap.put("testKey3", new Wrapper("value33", "value33"));
        expected = """
                {
                  "- testKey": "value2",
                  "+ testKey": "value1",
                  "- testKey1": "value21",
                  "+ testKey2": "value12",
                  "  testKey3": "value33"
                }
                """;
    }
    @Test
    void jsonFormat() {
        assertEquals(expected, JsonFormatter.jsonFormat(testMap));
    }
    @Test
    void lineRemoved() {
        assertTrue(true, String.valueOf(JsonFormatter.jsonFormat(testMap).contains("- testKey1\": \"value21")));
    }
    @Test
    void lineAdded() {
        assertTrue(true, String.valueOf(JsonFormatter.jsonFormat(testMap).contains("+ testKey2\": \"value12")));
    }
    @Test
    void sameLine() {
        assertTrue(true, String.valueOf(JsonFormatter.jsonFormat(testMap).contains("  testKey3\": \"value33")));
    }
}
