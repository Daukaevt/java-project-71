package hexlet.code;


import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;

public class MakingStyle {
    public static String makeStyle(String str) {
        var complexValue = str.replaceAll("\\[.*]|\\{.*}", "[complex value]");
        if (!str.equals(complexValue)) {
            return complexValue;
        } else if (!parseBoolean(str) && !isNumeric(str) && !str.equals("null")) {
            return "'" + str + "'";
        }
        return str;
    }
    public static boolean isNumeric(String str) {
        try {
            int intValue = parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }
}
