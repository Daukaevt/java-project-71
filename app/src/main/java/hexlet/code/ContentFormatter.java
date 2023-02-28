package hexlet.code;

import hexlet.code.formatters.FormattingJson;
import hexlet.code.formatters.FormattingPlain;

public class ContentFormatter {
    public static String makeStyle(String content, String format) {
        String contentStyled = null;
        if (format.equals("plain")) {
             contentStyled = FormattingPlain.format(content);
        } else if (format.equals("json")) {
            contentStyled = FormattingJson.format(content);
        } else {
            return content;
        }
        return contentStyled;
    }
}
