package hexlet.code.formatter;

import hexlet.code.utils.Wrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ContentFormatterTest {

    TreeMap<String, Wrapper> testMap = new TreeMap<>();
    String expected;
    @BeforeEach
    void setUp() {
        testMap.put("testKey", new Wrapper("value1", "value2"));
    }

    @Test
    void makePlain() {
        expected = "Property 'testKey' was updated. From 'value1' to 'value2'";

        assertEquals(expected, ContentFormatter.makeFormat(testMap, "plain"));
    }

    @Test
    void makeJson() {
        expected = """
                {
                  "- testKey": "value2",
                  "+ testKey": "value1"
                }
                """;
        assertEquals(expected, ContentFormatter.makeFormat(testMap, "json"));
    }

    @Test
    void makeStylish() {
        expected = """
                {
                  - testKey: value1
                  + testKey: value2
                }""";
        assertEquals(expected, ContentFormatter.makeFormat(testMap, "stylish"));
    }
}