package hexlet.code.CheckingDataFormat;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ChangingFormatFromYamlToJson {
    static String content;
    public static boolean changeFormat(final String path, final String s) {
        Path filePath = Path.of(path);
        var fileName = filePath.getFileName();
        try {
            content = DataFormating
                    .convertYamlToJson(Files.readString(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            File myObj = new File(s);
            if (myObj.createNewFile()) {
                try {
                    FileWriter myWriter = new FileWriter(s);
                    myWriter.write(content);
                    myWriter.close();
                    return true;
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return false;
    }
}
