package hexlet.code.formatter;


import hexlet.code.utils.Wrapper;

import java.util.TreeMap;

import static java.lang.Integer.parseInt;

public class PlainFormatter {
    public static String plainFormat(final TreeMap<String, Wrapper> unitMap) {
        StringBuilder stringBuilder = new StringBuilder();
        unitMap.keySet().forEach(key -> {
            Wrapper values = unitMap.get(key);
            String matrixFirstValue = values.getValue1();
            String matrixSecondValue = values.getValue2();
            if (matrixFirstValue.equals("-absent-")) {
                stringBuilder.append("Property '").append(key)
                        .append("' was added with value: ")
                        .append(getSingleQuotes3(matrixSecondValue)).append("\n");
            } else if (matrixSecondValue.equals("-absent-")) {
                stringBuilder.append("Property '").append(key)
                        .append("' was removed").append("\n");
            } else if (!matrixFirstValue.equals(matrixSecondValue)) {
                stringBuilder.append("Property '").append(key)
                        .append("' was updated. From ")
                        .append(getSingleQuotes3(matrixFirstValue)).append(" to ")
                        .append(getSingleQuotes3(matrixSecondValue)).append("\n");
            }
        });
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
