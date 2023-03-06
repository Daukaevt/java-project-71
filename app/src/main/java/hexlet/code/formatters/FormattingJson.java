package hexlet.code.formatters;

import com.google.common.base.Splitter;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FormattingJson {
    public static String format(String result) {
        JSONObject jsonResult;
        String cutResult = result
                .replaceAll("^.\n|..$", "");
        String trimedRes = cutResult.replaceAll(".$", "");
        if (trimedRes.endsWith("\n")) {
            trimedRes = trimedRes.substring(0, trimedRes.length()-1);
        }
        Map<String, String> properties = Splitter.on("\n")
                .withKeyValueSeparator(": ")
                .split(trimedRes);
        HashMap hashMap = new HashMap();
        for (String key:properties.keySet()) {
            hashMap.put(key.replaceAll("^.{2}", ""), properties.get(key));
        }
        jsonResult = new JSONObject(hashMap);
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(jsonResult));
        stringBuilder.insert(1, "\n")
                .insert(String.valueOf(jsonResult).length(), "\n");
        String str = stringBuilder.toString()
                .replaceFirst("\n", "\n  ")
                .replaceAll(".\",", "\",\n  ")
                .replaceAll("\":\"[\\[|\\{]", "\":\"");
        return str;
    }
}
