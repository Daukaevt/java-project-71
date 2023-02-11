package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Splitter;

import java.util.HashMap;
import java.util.Map;

public class FormattingJson {
    public static String format(String result) {
        String jsonResult;
        String cutResult = result
                .replaceAll("^.|.$", "")
                .replaceAll("\n", "`");
        String trimedRes = cutResult.replaceAll("^.|.$", "");
        Map<String, String> properties = Splitter.on("`")
                .withKeyValueSeparator(":")
                .split(trimedRes);
        try {
            jsonResult = new ObjectMapper().writeValueAsString(properties);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        StringBuilder stringBuilder = new StringBuilder(jsonResult);
        stringBuilder.insert(1, "\n")
                .insert(jsonResult.length(), "\n");
        String str = stringBuilder.toString().replaceAll("\",", "\",\n");
        return str;
    }
}
