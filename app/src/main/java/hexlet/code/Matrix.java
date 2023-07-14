package hexlet.code;


import hexlet.code.utils.Wrapper;

import java.util.Map;
import java.util.stream.Collectors;

public class Matrix {

    public static Map<String, Wrapper> createMatrix(Map<? extends String, ?> parseJson1,
            Map<? extends String, ?> parseJson2) {
        Map<String, Wrapper> matrixKey1EqualsKey2 = parseJson1.entrySet().stream()
                .filter(x -> parseJson2.containsKey(x.getKey()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        x -> new Wrapper(String.valueOf(x.getValue()),
                                String.valueOf(parseJson2.get(x.getKey())))));
        Map<String, Wrapper> matrixKey1NotEqualsKey2 = parseJson1.entrySet().stream()
                .filter(x -> !parseJson2.containsKey(x.getKey()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        x -> new Wrapper(String.valueOf(x.getValue()), "-absent-")));
        Map<String, Wrapper> matrixKey2NotEqualsKey1 = parseJson2.entrySet().stream()
                .filter(x -> !parseJson1.containsKey(x.getKey()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        x -> new Wrapper("-absent-", String.valueOf(x.getValue()))));
        matrixKey2NotEqualsKey1.putAll(matrixKey1EqualsKey2);
        matrixKey2NotEqualsKey1.putAll(matrixKey1NotEqualsKey2);
        return matrixKey2NotEqualsKey1;
    }
}
