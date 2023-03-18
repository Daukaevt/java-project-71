package hexlet.code;


import java.util.Map;

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
        Map<String, String> parseJson1 =
                Parser.parse(Reader.readFile(filePath1));
        Map<String, String> parseJson2 =
                Parser.parse(Reader.readFile(filePath2));
        String content = Parser.agregate(parseJson1, parseJson2);
     return ContentFormatter.makeStyle(content, format);
    }
}
