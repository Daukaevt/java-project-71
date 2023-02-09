package hexlet.code;

import java.util.*;

public class DataAggregating {
    static String[][] agregator;

    static int index;
    public static String[][] agregate(
            final Map parseJson1,
            final Map parseJson2
    ) {
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
        return agregator;
    }
    private static void setValue(int index, String temp, Object key) {
        agregator[0][index] = key.toString();
        agregator[1][index] = temp;
    }
}
