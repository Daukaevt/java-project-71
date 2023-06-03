package hexlet.code;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Reader {
    public static String read(final String filePath) {
        String content = null;
        try {
            content = Files.readString(Path.of(filePath));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return  !(content == null)
                ? content.replaceAll("\n$", "")
                : "";
    }
}
