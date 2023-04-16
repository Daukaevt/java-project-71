package hexlet.code;

import hexlet.code.utils.Wrapper;

import java.util.Map;
import java.util.TreeMap;

public class Uniter {
    public static TreeMap unite(
            final Map<String, Object> parseJson1,
            final Map<String, Object> parseJson2
    ) {
        TreeMap<String, Wrapper> unitMap = new TreeMap<>();
        for (Object key: parseJson1.keySet()) {
            if (parseJson2.containsKey(key)) {
                unitMap.put(key.toString(), new Wrapper(
                        (parseJson1.get(key).toString()),
                        (parseJson2.get(key).toString())));
            } else {
                unitMap.put(key.toString(), new Wrapper(
                        (parseJson1.get(key).toString()),
                        ("-absent-")
                ));
            }
        }
        for (Object key: parseJson2.keySet()) {
            if (!parseJson1.containsKey(key)) {
                unitMap.put(key.toString(), new Wrapper(
                        ("-absent-"),
                        (parseJson2.get(key).toString())
                ));
            }
        }
        return unitMap;
    }
}
