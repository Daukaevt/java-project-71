package hexlet.code;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Parser {
    public static Map parse(final String content) {
        boolean validJSON = isJSONValid(content);
        Map parseJsonToMap = null;
        if (validJSON) {
            parseJsonToMap = getData(String.valueOf(content));
        } else {
            String jsonContent = convertYamlToJson(content);
            try {
                parseJsonToMap = getData(jsonContent);
            }  catch (Exception e) {
                System.out.println("Wrong file format");
                System.exit(0);
            }
        }
        return replace(parseJsonToMap);
    }
    public static Map<String, String> replace(
            final Map parseFileContent) {
        Map<String, Object> map =
                new HashMap<>((Map<? extends String, ?>) parseFileContent);
        HashMap hashMap = new HashMap();
        for (String key: map.keySet()) {
            String value;
            map.putIfAbsent(key, "null");
            value = map.get(key).toString();
            if (value == null) {
                hashMap.put(key, "null");
            } else if (value.startsWith("[")) {
                value = value.replaceAll("^\\[", "")
                        .replaceAll("\\]$", "");
                Stream<String> stream = Arrays.stream(value.split(","));
                List<String> list = stream.collect(Collectors.toList());
                list.replaceAll(s -> s.replaceAll("^\"", "")
                        .replaceAll("\"$", ""));
                hashMap.put(key, list);
            } else if (value.startsWith("{")) {
                for (Object keyObj: parseFileContent.keySet()) {
                    String keyStr = keyObj.toString().replaceAll("^\"", "")
                            .replaceAll("\"$", "");
                    String valueStr = parseFileContent
                            .get(keyObj).toString().replaceAll("^\"", "")
                            .replaceAll("\"$", "");
                    hashMap.remove(key);
                    hashMap.put(keyStr, valueStr);
                }
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
                for (Object o : jsonArray) {
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        Map<String, String> map = mapper
                                .readValue(String.valueOf(o), Map.class);
                        for (Object key : map.keySet()) {
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
        } else if (contentFile1.isEmpty()) {
            return new HashMap<String, String>();
        } else {
            return getJsonObject(contentFile1);
        }
    }
    private static Map getJsonObject(final String contentFile1) {
        JSONObject jsonObject;
        JSONParser jsonParser = new JSONParser();
        try {
            jsonObject = (JSONObject) jsonParser.parse(contentFile1);
            return jsonObject;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean isJSONValid(final String jsonInString) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(jsonInString);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    public static String convertYamlToJson(final String yaml) {
        ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
        Object obj;
        try {
            obj = yamlReader.readValue(yaml, Object.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        ObjectMapper jsonWriter = new ObjectMapper();
        try {
            return jsonWriter.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    private static String[][] agregator;

    public static String agregate(
            final Map parseJson1, final Map parseJson2) {
        var size = parseJson1.size() + parseJson2.size();
        agregator = new String[2][size];
        int index = 0;
        String temp;
        for (Object key : parseJson1.keySet()) {
            if (parseJson2.containsKey(key)) {
                if (parseJson1.get(key).equals(parseJson2.get(key))
                ) {
                    temp = "    " + key + ": " + parseJson1.get(key);
                } else {
                    temp = "  - " + key + ": " + parseJson1.get(key);
                    setValue(index, temp, key);
                    index++;
                    temp = "  + " + key + ": " + parseJson2.get(key);
                }
            } else {
                temp = "  - " + key + ": " + parseJson1.get(key);
            }
            setValue(index, temp, key);
            index++;
        }
        for (Object key : parseJson2.keySet()) {
            if (!parseJson1.containsKey(key)) {
                temp = "  + " + key + ": " + parseJson2.get(key);
                setValue(index, temp, key);
                index++;
            }
        }
        return sort(checkForNull(agregator));
    }
    private static void setValue(
            final int index, final String temp, final Object key
    ) {
        agregator[0][index] = key.toString();
        agregator[1][index] = temp;
    }
    public static String[][] checkForNull(final String[][] comparedDatas) {
        for (int j = 0; j < comparedDatas.length; j++) {
            for (int i = 0; i < comparedDatas[j].length; i++) {
                if (comparedDatas[j][i] == null) {
                    comparedDatas[j][i] = "";
                }
            }
        }
        return comparedDatas;
    }
    public static String sort(final String[][] comparedData) {
        var tempArr = Arrays.stream(comparedData[0]).sorted().toArray();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");
        for (Object line: tempArr) {
            if (line.toString().equals("")) {
                continue;
            }
            for (int i = 0; i < comparedData[0].length; i++) {
                if (Objects.equals(line.toString(), comparedData[0][i])) {
                    stringBuilder.append(comparedData[1][i]).append("\n");
                    comparedData[0][i] = "";
                    comparedData[1][i] = "";
                }
            }
        }
        return stringBuilder.append("}").toString();
    }
}
