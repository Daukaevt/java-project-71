package hexlet.code;

import java.util.HashMap;
import java.util.Map;

public class CreatingNotNullContent {
    public static Map<String, Object> replaceNull(Map parseFileContent) {
        Map<String,Object> map = new HashMap<String, Object>((Map<? extends String, ?>) parseFileContent);
        for (String key: map.keySet()) {
            if (map.get(key) == null) {
                map.put(String.valueOf(key), "null");
            }
        }
        return map;
    }
}
