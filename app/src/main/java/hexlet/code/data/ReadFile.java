package hexlet.code.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadFile {
    public static String read(final String filePath1) throws Exception {
        Path path = Paths.get(filePath1).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            try {
                throw new Exception("File '" + path + "' does not exist");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        String content;
        try {
            content = Files.readString(path);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content;
    }
}
