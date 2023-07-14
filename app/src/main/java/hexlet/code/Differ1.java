package hexlet.code;


import hexlet.code.formatter.ContentFormatter;
import hexlet.code.utils.Wrapper;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Differ1 {
    public static String generate(final String firstFilePath, final String secondFilePath) throws Exception {
        return Differ1.generate(firstFilePath, secondFilePath, "stylish");
    }
    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        String contentFirst = Files.readString(Paths.get(filePath1).toAbsolutePath());
        Map content1ToMap = Parser1.parse(contentFirst, getDataFormat(filePath1));

        String contentSecond = Files.readString(Paths.get(filePath2).toAbsolutePath());
        Map content2ToMap = Parser1.parse(contentSecond, getDataFormat(filePath2));

        Map<String, Wrapper> unitMap1 = Uniter.matrix(content1ToMap, content2ToMap);
        var matrix = unitMap1.entrySet().stream()
                .sorted(Map.Entry.comparingByKey());
        Map<String, Wrapper> matrixMapper = matrix.collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
        TreeMap<String, Wrapper> sortedMatrixMapper = new TreeMap<>(matrixMapper);
        return ContentFormatter.makeFormat(sortedMatrixMapper, format);
    }
    static String getDataFormat(String filePath) {
        int index = filePath.lastIndexOf('.');
        return index > 0 ? filePath.substring(index + 1) : "";
    }
}
