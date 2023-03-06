package hexlet.code;

import java.util.*;

public class Parser {
    static String[][] agregator;
    static int index;
    public static String agregate(
            Map parseJson1, Map parseJson2) {
        var size = parseJson1.size() + parseJson2.size();
        agregator = new String[2][size];
        index = 0;
        String temp;
        for (Object key : parseJson1.keySet()) {
            if (parseJson2.containsKey(key)) {
                if (parseJson1.get(key).equals(parseJson2.get(key))
                ) {
                    temp = "  " + key + ": " + parseJson1.get(key);
                } else {
                    temp = "- " + key + ": " + parseJson1.get(key);
                    setValue(index, temp, key);
                    index++;
                    temp = "+ " + key + ": " + parseJson2.get(key);
                }
            } else {
                temp = "- " + key + ": " + parseJson1.get(key);
            }
            setValue(index, temp, key);
            index++;
        }
        for (Object key : parseJson2.keySet()) {
            if (!parseJson1.containsKey(key)) {
                temp = "+ " + key + ": " + parseJson2.get(key);
                setValue(index, temp, key);
                index++;
            }
        }
        return sort(checkForNull(agregator));
    }

    private static void setValue(int index, String temp, Object key) {
        agregator[0][index] = key.toString();
        agregator[1][index] = temp;
    }
    public static String[][] checkForNull(final String[][] comparedDatas) {
        for (int j = 0; j < comparedDatas.length; j++) {
            for (int i = 0; i < comparedDatas[j].length; i++) {
                if (comparedDatas[j][i] == null) {
                    comparedDatas[j][i] = "";
                }
            }
        }
        return comparedDatas;
    }

    public static String sort(final String[][] comparedData) {
        var tempArr = Arrays.stream(comparedData[0]).sorted().toArray();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");
        for (Object line: tempArr) {
            if (line.toString().equals("")) {
                continue;
            }
            for (int i = 0; i < comparedData[0].length; i++) {
                if (Objects.equals(line.toString(), comparedData[0][i])) {
                    stringBuilder.append(comparedData[1][i]).append("\n");
                    comparedData[0][i] = "";
                    comparedData[1][i] = "";
                }
            }
        }
        return stringBuilder.append("}").toString();
    }
}
