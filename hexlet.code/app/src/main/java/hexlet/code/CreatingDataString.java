package hexlet.code;

import java.util.Arrays;
import java.util.Objects;

public class CreatingDataString {
    public static String create(final String[][] comparedDatas) {
        var tempArr = Arrays.stream(comparedDatas[0]).sorted().toArray();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");
        for (Object line: tempArr) {
            if (line.toString().equals("")) {
                continue;
            }
            for (int i = 0; i < comparedDatas[0].length; i++) {
                if (Objects.equals(line.toString(), comparedDatas[0][i])) {
                    stringBuilder.append(comparedDatas[1][i]).append("\n");
                    comparedDatas[0][i] = "";
                    comparedDatas[1][i] = "";
                }
            }
        }
        return stringBuilder.append("}").toString();
    }
}
