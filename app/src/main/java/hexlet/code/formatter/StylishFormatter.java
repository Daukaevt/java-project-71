package hexlet.code.formatter;

import java.util.Map;

public class StylishFormatter {
    public static String stylishFormat(final Map stylishMap) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        for (Object key: stylishMap.keySet()) {
            String keyPostfix = null;
            String tempKey = key.toString();
            keyPostfix = tempKey.substring(tempKey.length() - 5);
            String keyContent = tempKey.substring(0, tempKey.length() - 5);
            switch (keyPostfix) {
                case ("minus"): {
                    stringBuilder.append("\n" + "  - " + keyContent + ": " + stylishMap.get(key));
                    break;
                }
                case ("plus "): {
                    stringBuilder.append("\n" + "  + " + keyContent + ": " + stylishMap.get(key));
                    break;
                }
                case ("space"): {
                    stringBuilder.append("\n" + "    " + keyContent + ": " + stylishMap.get(key));
                }
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + keyPostfix);
            }
        }
        return stringBuilder + "\n}";
    }
}
