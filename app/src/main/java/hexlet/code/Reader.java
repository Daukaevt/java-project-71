package hexlet.code;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    public static String readFile(final String filePath) {
        String content = null;
        try (BufferedReader br =
                     new BufferedReader(new FileReader(String.valueOf(filePath)))) {
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
