package hexlet.code.formatter;


import hexlet.code.utils.Wrapper;

import java.util.TreeMap;

import static java.lang.Integer.parseInt;

public class PlainFormatter {
    public static String plainFormate(final TreeMap<String, Wrapper> unitMap) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object key: unitMap.keySet()) {
            Wrapper values = unitMap.get(key);
            String value1 = values.getValue1();
            String value2 = values.getValue2();
            if (value1.equals("-absent-")) {
                stringBuilder.append("Property '" + key
                        + "' was added with value: "
                        + getSingleQuotes3(value2) + "\n");
                continue;
            }
            if (value2.equals("-absent-")) {
                stringBuilder.append("Property '"
                        + key + "' was removed" + "\n");
                continue;
            }
            if (!value1.equals(value2)) {
                stringBuilder.append("Property '" + key
                        + "' was updated. From " + getSingleQuotes3(value1)
                        + " to " + getSingleQuotes3(value2) + "\n");
            }
        }
        return stringBuilder.toString().replaceAll("\n$", "");
    }
    public static String getSingleQuotes3(final String value) {
        String singleQuotesEnclosedValue = value;
        if (!isBoolean(value)
                && !isNumeric(value)
                && !value.equals("null")) {
            singleQuotesEnclosedValue = "'" + value + "'";
        }
        return singleQuotesEnclosedValue
                .replaceAll("'\\[.*]'|'\\{.*}'", "[complex value]");
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
