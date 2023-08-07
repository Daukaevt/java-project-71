package hexlet.code.formatter;


import hexlet.code.utils.Wrapper;

import java.util.TreeMap;

public class JsonFormatter {
    public static String jsonFormat(final TreeMap<String, Wrapper> unitMap) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");
        for (Object key : unitMap.keySet()) {
            Wrapper values = unitMap.get(key);
            String matrixFirstValue = values.value1();
            String tempPlusLine = "  \"+ " + key + "\": \""
                    + matrixFirstValue.replaceAll("\"", "")
                    .replaceAll(",\\S", ", ") + "\",\n";
            String matrixSecondValue = values.value2();
            String tempMinusLine = "  \"- " + key + "\": \""
                    + matrixSecondValue.replaceAll("\"", "")
                    .replaceAll(",\\S", ", ") + "\",\n";
            if (matrixFirstValue.equals("-absent-")) {
                stringBuilder.append(tempMinusLine);
                continue;
            }
            if (matrixSecondValue.equals("-absent-")) {
                stringBuilder.append(tempPlusLine);
                continue;
            }
            if (matrixFirstValue.equals(matrixSecondValue)) {
                stringBuilder.append(tempMinusLine
                        .replaceAll(" {2}\"- ", "  \"  "));
            } else {
                stringBuilder.append(tempMinusLine);
                stringBuilder.append(tempPlusLine);
            }
        }
        return stringBuilder.toString().replaceAll(",$", "\n}");
    }
}
