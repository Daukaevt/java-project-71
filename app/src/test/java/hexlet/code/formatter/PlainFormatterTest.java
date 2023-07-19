package hexlet.code.formatter;

import hexlet.code.utils.Wrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class PlainFormatterTest {
    private TreeMap<String, Wrapper> testMap;
    private String expected;

    @BeforeEach
    void setUp() {
        testMap = new TreeMap<>();
        testMap.put("testKey1", new Wrapper("value1", "value2"));
        testMap.put("testKey2", new Wrapper("-absent-", "value22"));
        testMap.put("testKey3", new Wrapper("value13", "-absent-"));
        expected = """
                Property 'testKey1' was updated. From 'value1' to 'value2'
                Property 'testKey2' was added with value: 'value22'
                Property 'testKey3' was removed""";
    }

    @Test
    void plainFormate() {
        assertEquals(expected, PlainFormatter.plainFormat(testMap));
    }

    @Test
    void getSingleQuotes3() {
        assertEquals("'test'", PlainFormatter.getSingleQuotes3("test"));
    }

    @Test
    void isNumeric() {
        assertTrue(PlainFormatter.isNumeric("2"));
    }

    @Test
    void isBoolean() {
        assertTrue(PlainFormatter.isBoolean("false"));
    }

    @Test
    void builderContainsAdded() {
        assertTrue(PlainFormatter.plainFormat(testMap).contains("' was added with value: "));
    }
    @Test
    void builderContainsRemoved() {
        assertTrue(PlainFormatter.plainFormat(testMap).contains("' was removed"));
    }
}
