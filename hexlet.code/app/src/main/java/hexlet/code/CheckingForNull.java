package hexlet.code;

public class CheckingForNull {
    public static String[][] check(String[][] comparedDatas) {
        for (int j = 0; j < comparedDatas.length; j++) {
            for (int i = 0; i < comparedDatas[j].length; i++) {
                if (comparedDatas[j][i] == null) {
                    comparedDatas[j][i] = "";
                }
            }
        }
        return comparedDatas;
    }
}
