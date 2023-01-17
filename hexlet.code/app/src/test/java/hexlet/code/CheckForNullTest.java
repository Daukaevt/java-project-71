package hexlet.code;

import hexlet.code.CheckingForNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CheckForNullTest {
    CheckingForNull checkingForNull;
    String[][] testData;
    String[][] testData1;

    @BeforeEach
    void getTable() {
        checkingForNull = new CheckingForNull();
        testData = new String[2][2];
        testData1 = null;
        testData[0][0] = "anyData";
        testData[0][1] = "anotherData";
        testData[1][1] = null;
    }

    @Test
    void checkDataLengthTest() {
        var check = CheckingForNull.check(testData).length;
        assertEquals(2, check);
    }

    @Test
    void checkSingleLineDataIsNotNullTest() {
        assertEquals("", CheckingForNull.check(testData)[1][1]);
    }

    @Test
    void checkDataIsNotNullTest() {
        assertNotNull(testData);
    }
}
