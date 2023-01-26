package hexlet.code;

import java.util.HashMap;
import java.util.Map;

public class CreatingNotNullContent {
    public static Map replaceNull(Object parseFileContent) {
        Map<String,Object> map = new HashMap<String, Object>((Map<? extends String, ?>) parseFileContent);
        for (Object key: map.keySet()) {
            if (map.get(key) == null) {
                map.put(String.valueOf(key), "null");
            }
        }
        return map;
    }
}
