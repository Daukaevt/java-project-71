package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreatingDataStringTest {
    CreatingDataString creatingDataString;
    String[][] testArr;

    @BeforeEach
    void setUp() {
        creatingDataString = new CreatingDataString();
        testArr = new String[][]{
                {"host: ", "host: "},
                {"- host: jd.com", "+ host: tencentcloud.com"}};
    }

    @Test
    void create() {
        assertEquals("{\n" +
                "- host: jd.com\n" +
                "+ host: tencentcloud.com\n" +
                "}", creatingDataString.create(testArr));
    }
}