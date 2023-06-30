package hexlet.code.formatter;


import com.google.common.base.Splitter;
import com.google.gson.Gson;
import hexlet.code.utils.Wrapper;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;


public class ContentFormatter {
    public static String makeFormat(
            final TreeMap<String, Wrapper> unitMap,
            final String format
    ) throws Exception {
        switch (format) {
            case ("plain") -> {
                return PlainFormatter.plainFormat(addWhiteSpaces(unitMap));
            }
            case ("json") -> {
                return JsonFormatter.jsonFormat(addWhiteSpaces(unitMap));
            }
            case ("stylish") -> {
                return StylishFormatter.stylishFormat(addWhiteSpaces(unitMap));
            }
            default -> {
                throw new Exception("Unknown format: " + format);
            }
        }
    }
    public static TreeMap addWhiteSpaces(TreeMap<String, Wrapper> unitMap) {
    TreeMap<String, Wrapper> whiteSpacedUnitMap = new TreeMap<>();
    for (String key: unitMap.keySet()) {
        Wrapper values = unitMap.get(key);
        if (values != null) {
            whiteSpacedUnitMap.put(key,
                    new Wrapper(nestedData(values.getValue1()), nestedData(values.getValue2())));
        }
    }
    return whiteSpacedUnitMap;
}
    static String nestedData(String values) {
        if (values.startsWith("{") || values.endsWith("}")) {
            return mappingValue(values);
        } else if (values.startsWith("[{") || values.endsWith("}]")) {
            return "[" + mappingValue(values.substring(1, values.length() - 1)) + "]";
        } else if (values.startsWith("[") || values.endsWith("]")) {
            String arrayData = Arrays.toString(values.split(","));
            return arrayData.substring(1, arrayData.length() - 1);
        } else {
            return values;
        }
    }

    private static String mappingValue(String values) {
        Map<String, String> properties = Splitter.on(",")
                .withKeyValueSeparator(":")
                .split(values);
        return String.valueOf(properties).replaceAll("^.|.$", "");
    }

}
