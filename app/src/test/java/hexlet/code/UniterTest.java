package hexlet.code;

import hexlet.code.utils.Wrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UniterTest {
    String expected;
    HashMap<String, Object> firstData;
    HashMap<String, Object> secondData;
    TreeMap<String, Wrapper> unitedData;
    @BeforeEach
    void SetUp() {
        expected = "-absent-";
        firstData = new HashMap<>();
        secondData = new HashMap<>();
        firstData.put("profile", "{name=typicode}");
        firstData.put("posts", "[{id:1,title:hello}]");
        firstData.put("field", "null");
        secondData.put("fieldNew", "valueNew");
        unitedData = Uniter.unite(firstData, secondData);
    }

    @Test
    void unite() {
        assertEquals(expected, String.valueOf(unitedData.get("posts").getValue2()));
    }
    @Test
    void addWhiteSpaces() {
        TreeMap<String, Wrapper> testTreeMap = new TreeMap<>();
        testTreeMap.put("posts", new Wrapper("[{id:1,title:hello}]", "some value"));
        TreeMap<String, Wrapper> whiteSpacedTreeMap = Uniter.addWhiteSpaces(testTreeMap);
        String str = whiteSpacedTreeMap.get("posts").getValue1();
        assertEquals("[{id:1, title:hello}]", str);
    }
}
