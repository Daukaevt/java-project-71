package hexlet.code.formatter;

import hexlet.code.utils.Wrapper;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StylishFormatterTest {

    @Test
    void stylishFormat() {
        TreeMap<String, Wrapper> testMap = new TreeMap<>();
        testMap.put("testKey", new Wrapper("value1", "value2"));
        String expected = """
                {
                  - testKey: value1
                  + testKey: value2
                }""";
        assertEquals(expected, StylishFormatter.stylishFormat(testMap));
    }
}
