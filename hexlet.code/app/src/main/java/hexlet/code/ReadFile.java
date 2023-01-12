package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadFile {


    public static String read(String filePath1) throws Exception {

        // Формируем путь абсолютный путь,
        // если filePath будет содержать относительный путь,
        // то мы всегда будет работать с абсолютным
        Path path = Paths.get(filePath1).toAbsolutePath().normalize();


        // Проверяем существование файла
        if (!Files.exists(path)) {
            try {
                throw new Exception("File '" + path + "' does not exist");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        // Читаем файл
        String content;
        try {
            content = Files.readString(path);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return content;
    }
}
