package hexlet.code.formatter;

import hexlet.code.utils.Wrapper;

import java.util.TreeMap;

public class StylishFormatter {
    public static String stylishFormat(TreeMap<String, Wrapper> unitMap) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");
        for (Object key: unitMap.keySet()) {
            Wrapper values = unitMap.get(key);
            String value1 = values.getValue1();
            String value2 = values.getValue2();
            if (value1.equals("-absent-")) {
                stringBuilder.append("  + " + key + ": " + value2 + "\n");
                continue;
            }
            if (value2.equals("-absent-")) {
                stringBuilder.append("  - " + key + ": " + value1 + "\n");
                continue;
            }
            if (!value1.equals(value2)) {
                stringBuilder.append("  - " + key + ": " + value1 + "\n");
                stringBuilder.append("  + " + key + ": " + value2 + "\n");
            } else {
                stringBuilder.append("    " + key + ": " + value1 + "\n");
            }
        }
        return stringBuilder.toString() ;
    }
}
