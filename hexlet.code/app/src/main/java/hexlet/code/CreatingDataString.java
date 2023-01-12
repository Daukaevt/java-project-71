package hexlet.code;

import java.util.Arrays;

public class CreatingDataString {
    public static String create(String[][] comparedDatas) {
        var tempArr = Arrays.stream(comparedDatas[0]).sorted().toArray();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        for (Object line: tempArr) {
            for (int i = 0; i < comparedDatas[0].length; i++) {
                if (line.toString() == comparedDatas[0][i]) {
                    stringBuilder.append(comparedDatas[1][i]).append("\n");
                    comparedDatas[0][i] = "";
                    comparedDatas[1][i] = "";
                }
            }
        }
        return stringBuilder.append("}").toString();
    }
}
