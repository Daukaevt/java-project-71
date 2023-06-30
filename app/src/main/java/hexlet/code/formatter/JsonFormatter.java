package hexlet.code.formatter;


import hexlet.code.utils.Wrapper;

import java.util.TreeMap;

public class JsonFormatter {
    public static String jsonFormat(final TreeMap<String, Wrapper> unitMap) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");
        for (Object key : unitMap.keySet()) {
            Wrapper values = unitMap.get(key);
            String value1 = values.getValue1();
            String tempPlus = "  \"+ " + key + "\": \""
                    + value1.replaceAll("\"", "")
                    .replaceAll(",\\S", ",\s") + "\",\n";
            String value2 = values.getValue2();
            String tempMinus = "  \"- " + key + "\": \""
                    + value2.replaceAll("\"", "")
                    .replaceAll(",\\S", ",\s") + "\",\n";
            if (value1.equals("-absent-")) {
                stringBuilder.append(tempMinus);
                continue;
            }
            if (value2.equals("-absent-")) {
                stringBuilder.append(tempPlus);
                continue;
            }
            if (value1.equals(value2)) {
                stringBuilder.append(tempMinus
                        .replaceAll("\s{2}\"- ", "  \"  "));
            } else {
                stringBuilder.append(tempMinus);
                stringBuilder.append(tempPlus);
            }
        }
        return stringBuilder.toString().replaceAll(",$", "\n}");
    }
}
