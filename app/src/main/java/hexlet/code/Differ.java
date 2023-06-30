package hexlet.code;


import hexlet.code.formatter.ContentFormatter;
import hexlet.code.utils.Wrapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Differ {
    public static String generate(
            final String firstFilePath,
            final String secondFilePath
    ) throws Exception {
        return Differ.generate(
                firstFilePath, secondFilePath, "stylish");
    }
    public static String generate(
            final String filePath1, final String filePath2, final String format
    ) throws Exception {
        Path path1 = Paths.get(filePath1).normalize();
        Path path2 = Paths.get(filePath2).normalize();
        if (!Files.exists(path1) || !Files.exists(path2)) {
            return "File does not exist";
        }
        Map<String, Object> parseJson1 =
                Parser.parse(readContent(path1.toAbsolutePath().toString()),
                        getDataFormat(String.valueOf(path1)));
        Map<String, Object> parseJson2 =
                Parser.parse(readContent(path2.toAbsolutePath().toString()),
                        getDataFormat(String.valueOf(path2)));
        Map<String, Wrapper> unitMap1 = Uniter.matrix(parseJson1, parseJson2);
        var matrix = unitMap1.entrySet().stream()
                .sorted(Map.Entry.comparingByKey());
        Map<String, Wrapper> matrixMapper = matrix.collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
        TreeMap<String, Wrapper> sortedMatrixMapper = new TreeMap<>(matrixMapper);
        return ContentFormatter.makeFormat(sortedMatrixMapper, format);
    }
    private static String readContent(String filePath) throws IOException {
        return Files.readString(Path.of(filePath));
    }
    static String getDataFormat(String filePath) {
        int index = filePath.lastIndexOf('.');
        return index > 0
                ? filePath.substring(index + 1)
                : "";
    }
}
