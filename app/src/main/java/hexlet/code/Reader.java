package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.data.DataFormating;
import hexlet.code.data.JSONUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
            parseJson1 = getData(String.valueOf(content));
        } else {
            String jsonContent = DataFormating.convertYamlToJson(content);
            try {
                parseJson1 = getData(jsonContent);
            }  catch (Exception e) {
                System.out.println("Wrong file format");
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
                map.put(key, "null");
            }
            String value = map.get(key).toString();
            if (map.get(key) == null) {
                map.put(String.valueOf(key), "null");
            } else if (value.startsWith("[") || value.startsWith("{")) {
                map.put(key, value.replaceAll("\"", ""));
            }
        }
        return map;
    }
    public static Map getData(final String contentFile1) {
        if (contentFile1.startsWith("[")) {
            JSONArray jsonArray;
            JSONParser jsonParser = new JSONParser();
            try {
                jsonArray = (JSONArray) jsonParser.parse(contentFile1);
                HashMap hashMap = new HashMap();
                for (int i = 0; i < jsonArray.size(); i++) {
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        Map<String, String> map = mapper.readValue(String.valueOf(jsonArray.get(i)), Map.class);
                        for (Object key: map.keySet()) {
                            hashMap.put(key, map.get(key));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return hashMap;
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else {
            return getJsonObject(contentFile1);
        }
    }
    private static Map getJsonObject(String contentFile1) {
        JSONObject jsonObject;
        JSONParser jsonParser = new JSONParser();
        try {
            jsonObject = (JSONObject) jsonParser.parse(contentFile1);
            return jsonObject;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
