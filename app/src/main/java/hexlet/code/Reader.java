package hexlet.code;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Reader {
    public static String readFile(final String filePath) {
        String content = null;
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            try {
                throw new Exception("File '" + path + "' does not exist");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        try (BufferedReader br =
                     new BufferedReader(new FileReader(String.valueOf(path)))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
                content = sb.toString();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return !(content == null)
                ? content.replaceAll("\n$", "")
                : "";
    }
}
