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
        testMap.put("testKey", new Wrapper("value1", "value2"));
        expected = "Property 'testKey' was updated. From 'value1' to 'value2'";
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
}
