package hexlet.code.formatter;

import hexlet.code.utils.Wrapper;

import java.util.TreeMap;

public class StylishFormatter {
    public static String stylishFormat(final TreeMap<String, Wrapper> matrix) {
        StringBuilder stringBuilder = new StringBuilder();
        matrix.keySet().forEach(key -> {
            String matrixFirstValue = matrix.get(key).value1();
            String matrixSecondValue = matrix.get(key).value2();
            if ("-absent-".equals(matrixFirstValue)) {
                stringBuilder.append("  + ").append(key).append(": ").append(matrixSecondValue).append("\n"); return;
            }
            if ("-absent-".equals(matrixSecondValue)) {
                stringBuilder.append("  - ").append(key).append(": ").append(matrixFirstValue).append("\n"); return;
            }
            if (!matrixFirstValue.equals(matrixSecondValue)) {
                stringBuilder.append("  - ").append(key).append(": ")
                        .append(matrixFirstValue).append("\n");
                stringBuilder.append("  + ").append(key).append(": ")
                        .append(matrixSecondValue).append("\n");
            } else {
                stringBuilder.append("    ").append(key).append(": ")
                        .append(matrixFirstValue).append("\n");
            }
        });
        return "{\n" + stringBuilder + "}";
    }
}
