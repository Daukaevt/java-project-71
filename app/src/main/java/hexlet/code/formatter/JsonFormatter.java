package hexlet.code.formatter;


import hexlet.code.utils.Wrapper;

import java.util.TreeMap;

public class JsonFormatter {
    public static String jsonFormat(final TreeMap<String, Wrapper> unitMap) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");
        for (Object key : unitMap.keySet()) {
            Wrapper values = unitMap.get(key);
            String matrixFirstValue = values.getValue1();
            String tempPlusLine = "  \"+ " + key + "\": \""
                    + matrixFirstValue.replaceAll("\"", "")
                    .replaceAll(",\\S", ",\s") + "\",\n";
            String matrixSecondValue = values.getValue2();
            String tempMinusLine = "  \"- " + key + "\": \""
                    + matrixSecondValue.replaceAll("\"", "")
                    .replaceAll(",\\S", ",\s") + "\",\n";
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
                        .replaceAll("\s{2}\"- ", "  \"  "));
            } else {
                stringBuilder.append(tempMinusLine);
                stringBuilder.append(tempPlusLine);
            }
        }
        return stringBuilder.toString().replaceAll(",$", "\n}");
    }
}
