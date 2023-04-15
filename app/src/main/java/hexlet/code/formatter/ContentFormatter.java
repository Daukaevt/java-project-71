package hexlet.code.formatter;


import hexlet.code.utils.Wrapper;

import java.util.TreeMap;


public class ContentFormatter {
    public static String makeStyle1(
            final TreeMap<String, Wrapper> unitMap,
            final String format
    ) {
        switch (format) {
            case ("plain") -> {
                return PlainFormatter.plainFormate(unitMap);
            }
            case ("json") -> {
                return JsonFormatter.jsonFormat(unitMap);
            }
            default -> {
                return StylishFormatter.stylishFormat(unitMap);
            }
        }
    }
}