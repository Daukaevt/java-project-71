package hexlet.code.formatter;

import hexlet.code.utils.Wrapper;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StylishFormatterTest {
    TreeMap<String, Wrapper> testMap;
    String expected;

    @Test
    void stylishFormat() {
        testMap = new TreeMap<>();
        testMap.put("testKey", new Wrapper("value1", "value2"));
        expected = """
                {
                  - testKey: value1
                  + testKey: value2
                }""";
        assertEquals(expected, StylishFormatter.stylishFormat(testMap));
    }
}