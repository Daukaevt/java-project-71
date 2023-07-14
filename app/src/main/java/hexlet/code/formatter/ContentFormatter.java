package hexlet.code.formatter;


import hexlet.code.utils.Wrapper;

import java.util.TreeMap;


public class ContentFormatter {
    public static String makeFormat(
            final TreeMap<String, Wrapper> unitMap,
            final String format
    ) throws Exception {
        switch (format) {
            case ("plain") -> {
                return PlainFormatter.plainFormat(unitMap);
            }
            case ("json") -> {
                return JsonFormatter.jsonFormat(unitMap);
            }
            case ("stylish") -> {
                return StylishFormatter.stylishFormat(unitMap);
            }
            default -> throw new Exception("Unknown format: " + format);
        }
    }
}
