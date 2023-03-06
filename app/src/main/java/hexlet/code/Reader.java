package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Splitter;
import hexlet.code.data.DataFormating;
import hexlet.code.data.JSONUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Reader {
    public static Map check(final String filePath) {
        String content;
        try {
            content = read(filePath);
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
        return replace(parseJson1);
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
        String content1;
        BufferedReader br = new BufferedReader(new FileReader(filePath1));
        try {

            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            content1 = sb.toString();
        } finally {
            br.close();
        }
        return content1;
    }
    public static Map<String, Object> replace(Map parseFileContent) {
        Map<String,Object> map = new HashMap<>((Map<? extends String, ?>) parseFileContent);
        HashMap hashMap = new HashMap();
        for (String key: map.keySet()) {
            String value;
            if (map.get(key) == null) {
                map.put(key, "null");
            }
            value = map.get(key).toString();
            if (value == null) {
                hashMap.put(key, "null");
            } else if (value.startsWith("[")) {
               value = value.replaceAll("^\\[", "")
                        .replaceAll("\\]$", "");
                Stream<String> stream = Arrays.stream(value.split( "," ));
                List<String> list = stream.collect(Collectors.toList());
                for (int i = 0; i < list.size(); i++) {
                    String str = list.get(i).replaceAll("^\"", "")
                            .replaceAll("\"$", "");
                    list.set(i, str);
                }
                hashMap.put(key, list);
            } else if (value.startsWith("{")) {
                value = value.replaceAll("^[{]", "")
                        .replaceAll("[}]$", "");
                Map<String, String> properties = Splitter.on(",")
                        .withKeyValueSeparator(":")
                        .split(value);
                Map newMap = new HashMap();
                for (Object keyObj: properties.keySet()) {
                    String keyStr = keyObj.toString().replaceAll("^\"", "")
                            .replaceAll("\"$", "");
                    String valueStr = properties.get(keyObj).toString().replaceAll("^\"", "")
                            .replaceAll("\"$", "");
                    newMap.put(keyStr, valueStr);
                }
                hashMap.put(key, newMap);
            } else {
                hashMap.put(key, map.get(key));
            }
        }
        return hashMap;
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
