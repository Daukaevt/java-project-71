package hexlet.code;


import java.util.Map;

public class Differ {
    public static String generate (String firstFilePath, String secondFilePath) throws Exception {
        return Differ.generate(firstFilePath, secondFilePath, "stylish");
    }
    public static String generate(
            String filePath1, String filePath2, String format) throws Exception {
            Map parseJson1 = Reader.check(filePath1);
            Map parseJson2 = Reader.check(filePath2);
            String content = Parser.agregate(parseJson1, parseJson2);
            content = ContentFormatter.makeStyle(content, format);
     return content;
    }
}
