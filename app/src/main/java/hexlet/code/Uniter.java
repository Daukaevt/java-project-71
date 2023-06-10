package hexlet.code;


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
                        ("-absent-")));
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
            if (values != null) {
                whiteSpacedUnitMap.put(key,
                        new Wrapper(nestedData(values.getValue1()), nestedData(values.getValue2())));
            }
        }
        return whiteSpacedUnitMap;
    }
    static String nestedData(String values) {
        if (values.startsWith("{") || values.endsWith("}")) {
            return mappingValue(values);
        } else if (values.startsWith("[{") || values.endsWith("}]")) {
            return "[" + mappingValue(values.substring(1, values.length() - 1)) + "]";
        } else return values;
    }

    private static String mappingValue(String values) {
        HashMap tempValuesMap = new HashMap();
            String tempValues = values.substring(1, values.length() - 1);
            String[] splitedLines = tempValues.split(",");
            for (String splitedLine : splitedLines) {
                String[] keyValuePares = splitedLine.split(":");
                tempValuesMap.put(keyValuePares[0], keyValuePares[1]);
            }
        return String.valueOf(tempValuesMap);
    }
}
