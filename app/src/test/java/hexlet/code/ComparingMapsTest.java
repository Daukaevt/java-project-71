package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ComparingMapsTest {
    ComparingMaps comparingMaps = new ComparingMaps();
    Map <String, String> testMap1;
    Map <String, String> testMap2;

    @BeforeEach
    void setUp() {
        testMap1 = new HashMap <>();
        testMap1.put("host", "jd.com");
        testMap2 = new HashMap <>();
        testMap2.put("host", "tencentcloud.com");
    }

    @Test
    void compare() {
        String[][] testArr = comparingMaps.compare(testMap1, testMap2);
        assertEquals("- host: jd.com", testArr[1][0]);
    }
}