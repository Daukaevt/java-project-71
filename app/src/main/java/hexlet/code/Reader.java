package hexlet.code;


import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Reader {
    public static String readFile(final String filePath) {
        String content;
        try {
            content = read(filePath);
        } catch (Exception e) {
            System.out.println("File '" + filePath + "' does not exist.");
            throw new RuntimeException(e);
        }
        return content;
    }
    public static String read(final String filePath1) throws Exception {
        Path path = Paths.get(filePath1).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            try {
                throw new Exception("File '" + path + "' does not exist");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        String content1 = null;
        try (BufferedReader br =
                     new BufferedReader(new FileReader(filePath1))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            if (sb != null) {
                content1 = sb.toString();
            } else {
                System.out.println("null");
            }
        }
        return content1;
    }
}
