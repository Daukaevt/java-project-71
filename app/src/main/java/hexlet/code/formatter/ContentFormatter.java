package hexlet.code.formatter;


import hexlet.code.utils.Wrapper;

import java.util.TreeMap;


public class ContentFormatter {
    public static String makeFormat(
            final TreeMap<String, Wrapper> matrix,
            final String format
    ) throws Exception {
        switch (format) {
            case ("plain") -> {
                return PlainFormatter.plainFormat(matrix);
            }
            case ("json") -> {
                return JsonFormatter.jsonFormat(matrix);
            }
            case ("stylish") -> {
                return StylishFormatter.stylishFormat(matrix);
            }
            default -> throw new Exception("Unknown format: " + format);
        }
    }
}
