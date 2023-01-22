package hexlet.code.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JSONUtilsTest {
    JSONUtils jsonUtils;
    @BeforeEach
    void setUp() {
        jsonUtils = new JSONUtils();
    }

    @Test
    void isNotValid() {
        boolean isValid = jsonUtils.isJSONValid("test");
        assertEquals(false, isValid);
    }
    @Test
    void isValid() {
        boolean isValid = jsonUtils.isJSONValid("{\"Key\": \"Value\"}");
        assertEquals(true, isValid);
    }
}