package hexlet.code.formatter;

import hexlet.code.utils.Wrapper;

import java.util.TreeMap;

public class StylishFormatter {
    public static String stylishFormat(final TreeMap<String, Wrapper> unitMap) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");
        unitMap.keySet().forEach(key -> {
            Wrapper values = unitMap.get(key);
            String value1 = values.getValue1();
            String value2 = values.getValue2();
            if (value1.equals("-absent-")) {
                stringBuilder.append("  + ")
                        .append(key)
                        .append(": ")
                        .append(value2)
                        .append("\n");
                return;
            }
            if (value2.equals("-absent-")) {
                stringBuilder.append("  - ")
                        .append(key)
                        .append(": ")
                        .append(value1)
                        .append("\n");
                return;
            }
            if (!value1.equals(value2)) {
                stringBuilder.append("  - ")
                        .append(key)
                        .append(": ")
                        .append(value1)
                        .append("\n");
                stringBuilder.append("  + ")
                        .append(key)
                        .append(": ")
                        .append(value2)
                        .append("\n");
            } else {
                stringBuilder.append("    ")
                        .append(key)
                        .append(": ")
                        .append(value1)
                        .append("\n");
            }
        });
        return stringBuilder + "}";
    }
}
