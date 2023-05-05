package hexlet.code.formatter;

import hexlet.code.utils.Wrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StylishFormatterTest {
    private TreeMap<String, Wrapper> testMap;
    private String expected;
    @BeforeEach
    void setUp() {
        testMap = new TreeMap<>();
    }

    @Test
    void stylishFormat() {
        testMap.put("testKey", new Wrapper("value1", "value2"));
        expected = """
                {
                  - testKey: value1
                  + testKey: value2
                }""";
        assertEquals(expected, StylishFormatter.stylishFormat(testMap));
    }

    @Test
    void ifValue1Absent() {
        testMap.put("testKey", new Wrapper("-absent-", "value2"));
        testMap.put("testKey2", new Wrapper("value1", "-absent-"));
        testMap.put("testKey3", new Wrapper("value3", "value3"));
        expected = """
                {
                  + testKey: value2
                  - testKey2: value1
                    testKey3: value3
                }""";
        assertEquals(expected, StylishFormatter.stylishFormat(testMap));
    }
}
