package hexlet.code;

import hexlet.code.utils.Wrapper;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UniterTest {

    @Test
    void unite() {
        String expected = "-absent-";
        HashMap<String, Object> firstData = new HashMap<>();
        HashMap<String, Object> secondData = new HashMap<>();
        firstData.put("profile", "{name=typicode}");
        firstData.put("posts", "[{id:1, title:hello}]");
        firstData.put("field", "null");
        secondData.put("fieldNew", "valueNew");
        TreeMap<String, Wrapper> unitedData = Uniter.unite(firstData, secondData);
        assertEquals(expected, String.valueOf(unitedData.get("posts").getValue2()));
    }
}
