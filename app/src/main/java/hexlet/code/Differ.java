package hexlet.code;


import hexlet.code.formatter.ContentFormatter;
import hexlet.code.utils.Wrapper;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

public class Differ {
    public static String generate(final String firstFilePath, final String secondFilePath) throws Exception {
        return Differ.generate(firstFilePath, secondFilePath, "stylish");
    }
    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map firstContentMapper = Parser.parse(
                Files.readString(Paths.get(filePath1).toAbsolutePath()),
                getDataFormat(filePath1));
        Map secondContentMapper = Parser.parse(
                Files.readString(Paths.get(filePath2).toAbsolutePath()),
                getDataFormat(filePath2));
        Map<String, Wrapper> matrix = Matrix.createMatrix(firstContentMapper, secondContentMapper);
        TreeMap<String, Wrapper> sortedMatrixMapper = new TreeMap<>(matrix);
        return ContentFormatter.makeFormat(sortedMatrixMapper, format);
    }
    static String getDataFormat(String filePath) {
        int index = filePath.lastIndexOf('.');
        return index > 0 ? filePath.substring(index + 1) : "";
    }
}
