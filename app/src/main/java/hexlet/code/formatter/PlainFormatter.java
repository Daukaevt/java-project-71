package hexlet.code.formatter;


import java.util.Map;
import java.util.TreeMap;

import static java.lang.Integer.parseInt;

public class PlainFormatter {
    public static String plainFormate2(
            Map<String, Object> parseJson1, Map<String, Object> parseJson2) {
        TreeMap treeMap = new TreeMap();
        StringBuilder stringBuilder = new StringBuilder();
        for (Object key : parseJson1.keySet()) {
            if (parseJson2.containsKey(key)) {
                if (!parseJson1.get(key).equals(parseJson2.get(key))) {
                    treeMap.put(key, "  Property '" + key + "' was updated. From "
                            + getSingleQuotes(parseJson1.get(key).toString()) + " to "
                            + getSingleQuotes(parseJson2.get(key).toString()));
                } else {
                    if (parseJson1.get(key).toString().contains("\\[.*]|\\{.*}")) {
                        treeMap.put(key, "  Property '" + key + "' was updated. From "
                                + parseJson1.get(key) + " to "
                                + getSingleQuotes(parseJson2.get(key).toString()));
                    }
                }
            } else {
                treeMap.put(key, "  Property '" + key + "' was removed");
            }
        }
        for (Object key : parseJson2.keySet()) {
            if (!parseJson1.containsKey(key)) {
                treeMap.put(key, "  Property '" + key + "' was added with value: "
                        + getSingleQuotes(parseJson2.get(key).toString()));
            }
        }
        Map getComplexValuesMap = getComplexValues(treeMap);
        for (Object key: getComplexValuesMap.keySet()) {
            stringBuilder.append(getComplexValuesMap.get(key) + "\n");
        }

        return stringBuilder.toString();
    }

    private static TreeMap getComplexValues(Map<String, Object> parseJson) {
        TreeMap map1 = new TreeMap();
        for (String key : parseJson.keySet()) {
            String value = String.valueOf(parseJson.get(key));
            var complexValue = value.replaceAll("'\\[.*?]'|\\{.*?}'", "[complex value]");
            if (!value.equals(complexValue)) {
                value = complexValue;
            }
            map1.put(key, value);
        }
        return map1;
    }
    public static String getSingleQuotes(String value) {
        if (!value.contains("\\[.*]|\\{.*}")
                && !isBoolean(value)
                && !isNumeric(value)
                && !value.equals("null")) {
            value = "'" + value + "'";
        }
        return value;
    }

    public static boolean isNumeric(final String str) {
        try {
            parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isBoolean(final String str) {
        return str.equals("true") || str.equals("false");
    }
}
