package hexlet.code;

import hexlet.code.data.DataFormating;
import hexlet.code.data.JSONUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Reader {
    public static Map check(final String filePath) {
        String content;
        try {
            content = hexlet.code.data.ReadFile.read(filePath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        boolean validJSON = JSONUtils.isJSONValid(content);
        Map parseJson1 = null;
        if (validJSON) {

            parseJson1 = hexlet.code.JacksonObjectMapperMakingHostAddresses
                    .getData(String.valueOf(content));
        } else {
            String jsonContent = DataFormating.convertYamlToJson(content);
            try {
                parseJson1 = hexlet.code.JacksonObjectMapperMakingHostAddresses
                        .getData(jsonContent);
            }  catch (Exception e) {
                System.out.println("Wrong file format"); //+ e.getMessage());
                System.exit(0);
            }
        }
        return replaceNull(parseJson1);
    }
    public static String read(String filePath1) throws Exception {
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
    public static Map<String, Object> replaceNull(Map parseFileContent) {
        Map<String,Object> map = new HashMap<>((Map<? extends String, ?>) parseFileContent);
        for (String key: map.keySet()) {
            if (map.get(key) == null) {
                map.put(String.valueOf(key), "null");
            }
        }
        return map;
    }
}
