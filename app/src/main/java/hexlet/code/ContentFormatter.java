package hexlet.code;

import com.google.common.base.Splitter;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;


public class ContentFormatter {
    public static final int NO_TRAILING_SPACES_INDEX = 4;
    public static String makeStyle(final String content, final String format) {
        String contentStyled;
        if (format.equals("plain")) {
             contentStyled = plainFormat(content);
        } else if (format.equals("json")) {
            contentStyled = jsonFormat(content);
        } else {
            return content;
        }
        return contentStyled;
    }
    public static String jsonFormat(final String result) {
        JSONObject jsonResult;
        String cutResult = result
                .replaceAll("^.\n|..$", "");
        String trimedRes = cutResult.replaceAll(".$", "");
        if (trimedRes.endsWith("\n")) {
            trimedRes = trimedRes.substring(0, trimedRes.length() - 1);
        }
        Map<String, String> properties = Splitter.on("\n")
                .withKeyValueSeparator(": ")
                .split(trimedRes);
        HashMap<String, String> hashMap = new HashMap();
        for (String key:properties.keySet()) {
            hashMap.put(key.replaceAll("^.{2}", ""), properties.get(key));
        }
        jsonResult = new JSONObject(hashMap);
        StringBuilder stringBuilder =
                new StringBuilder(String.valueOf(jsonResult));
        stringBuilder.insert(1, "\n")
                .insert(String.valueOf(jsonResult).length(), "\n");
        return stringBuilder.toString()
                .replaceFirst("\n", "\n  ")
                .replaceAll(".\",", "\",\n  ")
                .replaceAll("\":\"[\\[|\\{]", "\":\"");
    }

    public static String plainFormat(final String result) {
        var tmpText = result.replaceAll("^.|.$", "");
        var tmpLines = tmpText.lines();
        String secondValue = "";
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<String> tmpArrayList = new ArrayList<>();
        tmpLines.forEach(arrayList::add);
        arrayList.remove(0);
        for (int i = 0; i < arrayList.size(); i++) {
            String tmpSubstringPlus;
            String line = arrayList.get(i);
            var key = line.substring(NO_TRAILING_SPACES_INDEX)
                    .replaceAll(":\s.*", "");
            String firstValue = makeStyle(line.replaceAll(".*:\s", ""));
            if (i + 1 < arrayList.size()) {
                secondValue  = makeStyle(arrayList.get(i + 1)
                        .replaceAll(".*:\s", ""));
            }
            switch (line.charAt(2)) {
                case '-' -> {
                    if (i + 1 < arrayList.size()) {
                        if (arrayList.get(i + 1).contains(key)) {
                            String tempStrMinusPlus = "Property '"
                                    + key + "' was updated. From "
                                    + firstValue + " to " + secondValue;
                            tmpArrayList.add(tempStrMinusPlus);
                            i++;
                        } else {
                            String tempStrMinus = "Property '"
                                    + key + "' was removed";
                            tmpArrayList.add(tempStrMinus);
                        }
                    } else {
                        String tempStrMinus = "Property '"
                                + key + "' was removed";
                        tmpArrayList.add(tempStrMinus);
                    }
                }
                case '+' -> {
                    tmpSubstringPlus = "Property '"
                            + key + "' was added with value: " + firstValue;
                    tmpArrayList.add(tmpSubstringPlus);
                }
                default -> { }
            }
        }
        return createResult(tmpArrayList);
    }
    private static String createResult(final ArrayList<String> tmpArrayList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String line: tmpArrayList) {
            stringBuilder.append(line).append("\n");
        }
        return stringBuilder.replace(stringBuilder.length() - 1,
                stringBuilder.length(), "").toString();
    }
    public static String makeStyle(final String str) {
        var complexValue = str.replaceAll("\\[.*]|\\{.*}", "[complex value]");
        if (!str.equals(complexValue)) {
            return complexValue;
        } else if (!isBoolean(str) && !isNumeric(str) && !str.equals("null")) {
            return "'" + str + "'";
        }
        return str;
    }
    public static boolean isNumeric(final String str) {
        try {
            parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean isBoolean(final String str) {
        return str.equals("true") || str.equals("false");
    }
}
