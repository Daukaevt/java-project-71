package hexlet.code;

import java.util.*;

public class ComparingMaps {
    public static String[][] compare(
            final Map parseJson1,
            final Map parseJson2
    ) {
        String[][] comparedDatas =
                new String[2][parseJson1.size() + parseJson2.size()];
        int index = 0;
        String temp;
        for (Object key: parseJson1.keySet()) {
            if (parseJson2.containsKey(key)) {
                if (parseJson1.get(key).equals(parseJson2.get(key))) {
                    temp = "  " + key + ": " + parseJson1.get(key);
                } else {
                    temp = "- " + key + ": " + parseJson1.get(key);
                    comparedDatas[0][index] = key.toString();
                    comparedDatas[1][index] = temp;
                    index++;
                    temp = "+ " + key + ": " + parseJson2.get(key);
                }
                comparedDatas[0][index] = key.toString();
                comparedDatas[1][index] = temp;
                index++;
            } else {
                temp = "- " + key + ": " + parseJson1.get(key);
                comparedDatas[0][index] = key.toString();
                comparedDatas[1][index] = temp;
                index++;
            }
        }
        for (Object key: parseJson2.keySet()) {
            if (!parseJson1.containsKey(key)) {
                temp = "+ " + key + ": " + parseJson2.get(key);
                comparedDatas[0][index] = key.toString();
                comparedDatas[1][index] = temp;
                index++;
            }
        }
        return comparedDatas;
    }

}
