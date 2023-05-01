package hexlet.code;


import hexlet.code.formatter.ContentFormatter;
import hexlet.code.utils.Wrapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

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
                Parser.parse(Reader.readFile(path1.toAbsolutePath().toString()));

        Map<String, Object> parseJson2 =
                Parser.parse(Reader.readFile(path2.toAbsolutePath().toString()));
        TreeMap<String, Wrapper> unitMap = Uniter.unite(parseJson1, parseJson2);
        return ContentFormatter.makeFormat(unitMap, format);
    }
}
