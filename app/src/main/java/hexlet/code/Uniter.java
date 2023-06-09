package hexlet.code;

import com.google.common.base.Splitter;
import hexlet.code.utils.Wrapper;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Uniter {
    public static TreeMap unite(
            final Map<String, Object> parseJson1,
            final Map<String, Object> parseJson2
    ) {
        TreeMap<String, Wrapper> unitMap = new TreeMap<>();
        for (Object key: parseJson1.keySet()) {
            if (parseJson2.containsKey(String.valueOf(key))) {
                unitMap.put(key.toString(), new Wrapper(
                        (parseJson1.get(String.valueOf(key)).toString()),
                        (parseJson2.get(String.valueOf(key)).toString())));
            } else {
                unitMap.put(key.toString(), new Wrapper(
                        (parseJson1.get(String.valueOf(key)).toString()),
                        ("-absent-")
                ));
            }
        }
        for (Object key: parseJson2.keySet()) {
            if (!parseJson1.containsKey(String.valueOf(key))) {
                unitMap.put(key.toString(), new Wrapper(
                        ("-absent-"),
                        (parseJson2.get(String.valueOf(key)).toString())));
            }
        }
        return addWhiteSpaces(unitMap);
    }
    public static TreeMap addWhiteSpaces(TreeMap<String, Wrapper> unitMap) {
        TreeMap<String, Wrapper> whiteSpacedUnitMap = new TreeMap<>();
        for (String key: unitMap.keySet()) {
            Wrapper values = unitMap.get(key);
            whiteSpacedUnitMap.put(key, new Wrapper(checkingValue(values.getValue1()), checkingValue(values.getValue2())
            ));
        }
        return whiteSpacedUnitMap;
    }

    @SuppressWarnings("UnstableApiUsage")
    private static String checkingValue(String values) {
        if (values.startsWith("{") || values.startsWith("[")) {
            Splitter.MapSplitter mapSplitter = Splitter.on(",").withKeyValueSeparator(":");
            Map<String, String> result = mapSplitter.split(values);
            Map<String, String> adjustedMap = new HashMap<>(result);
            adjustedMap.replaceAll ((k,v) -> v != null ? v : null);
            var length = result.toString().length() - 1;
            return result.toString().substring(1, length);
        } else return values;
    }
}
