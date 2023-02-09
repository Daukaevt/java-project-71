package hexlet.code;

import java.util.Arrays;
import java.util.Objects;

public class SortingDataString {
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
