package hexlet.code.formatter;


import hexlet.code.utils.Wrapper;

import java.util.TreeMap;


public class ContentFormatter {
    public static String makeStyle1(
            TreeMap<String, Wrapper> unitMap,
            final String format
    ) {
        switch (format) {
            case ("plain") -> {
                return PlainFormatter.plainFormate3(unitMap);
            }
            case ("json") -> {
                return JsonFormatter.jsonFormat2(unitMap);
            }
            default -> {
                return StylishFormatter.stylishFormat1(unitMap);
            }
        }
    }
}
