package hexlet.code;

import hexlet.code.utils.Wrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MatrixTest {
    private String expected;
    private Map<String, Wrapper> unitedData;
    @BeforeEach
    void setUp() {
        expected = "-absent-";
        HashMap<String, Object> firstData = new HashMap<>();
        HashMap<String, Object> secondData = new HashMap<>();
        firstData.put("profile", "{name:typicode}");
        firstData.put("posts", "[{id:1,title:hello}]");
        firstData.put("field", "null");
        secondData.put("fieldNew", "valueNew");
        unitedData = Matrix.createMatrix(firstData, secondData);
    }

    @Test
    void unite() {
        assertEquals(expected, String.valueOf(unitedData.get("posts").value2()));
    }

}
