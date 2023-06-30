package hexlet.code.formatter;

import hexlet.code.utils.Wrapper;

import java.util.TreeMap;

public class StylishFormatter {
    public static String stylishFormat(final TreeMap<String, Wrapper> unitMap) {
        StringBuilder stringBuilder = new StringBuilder();
        unitMap.keySet().forEach(key -> {
            String value1 = unitMap.get(key).getValue1();
            String value2 = unitMap.get(key).getValue2();
            if ("-absent-".equals(value1)) {
                stringBuilder.append("  + ").append(key).append(": ").append(value2).append("\n"); return;
            }
            if ("-absent-".equals(value2)) {
                stringBuilder.append("  - ").append(key).append(": ").append(value1).append("\n"); return;
            }
            if (!value1.equals(value2)) {
                stringBuilder.append("  - ").append(key).append(": ")
                        .append(value1).append("\n");
                stringBuilder.append("  + ").append(key).append(": ")
                        .append(value2).append("\n");
            } else {
                stringBuilder.append("    ").append(key).append(": ")
                        .append(value1).append("\n");
            }
        });
        return "{\n" + stringBuilder + "}";
    }
}
