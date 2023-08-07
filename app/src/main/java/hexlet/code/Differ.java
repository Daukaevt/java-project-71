package hexlet.code;


import hexlet.code.formatter.ContentFormatter;
import hexlet.code.utils.Wrapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Differ {
    public static String generate(final String firstFilePath, final String secondFilePath) throws Exception {
        return Differ.generate(firstFilePath, secondFilePath, "stylish");
    }
    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Wrapper> firstContentMapper = null;
        Map<String, Wrapper> secondContentMapper = null;
        if (isExist(filePath1)) {
            firstContentMapper = Parser.parse(
                    Files.readString(Paths.get(filePath1).toAbsolutePath()),
                    getDataFormat(filePath1));
        }
        if (isExist(filePath2)) {
            secondContentMapper = Parser.parse(
                    Files.readString(Paths.get(filePath2).toAbsolutePath()),
                    getDataFormat(filePath2));
        }
        Map<String, Wrapper> matrix = Matrix.createMatrix(
                Objects.requireNonNull(firstContentMapper),
                Objects.requireNonNull(secondContentMapper));
        TreeMap<String, Wrapper> sortedMatrixMapper = new TreeMap<>(matrix);
        return ContentFormatter.makeFormat(sortedMatrixMapper, format);
    }
    private static String getDataFormat(String filePath) {
        int index = filePath.lastIndexOf('.');
        return index > 0 ? filePath.substring(index + 1) : "";
    }
    private static boolean isExist(String filePath) throws Exception {
        if (!Files.exists(Path.of(filePath))) {
            throw new Exception("File '" + filePath + "' does not exist");
        }
        return true;
    }
}
