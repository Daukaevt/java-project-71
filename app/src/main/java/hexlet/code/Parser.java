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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Parser {
    public static Map parse(final String content) {
        if (content.isEmpty()) {
            return new HashMap<String, String>();
        }
        boolean validJSON = isJSONValid(content);
        Map parseJsonToMap = null;
        if (validJSON) {
            parseJsonToMap = getJson(content);
        } else {
            String jsonContent = convertYamlToJsonData(content);
            try {
                parseJsonToMap = getJson(jsonContent);
            } catch (Exception e) {
                System.out.println("Wrong file format");
                System.exit(0);
            }
        }
        return replaceNull(parseJsonToMap);
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
    private static Map getJson(String content) {
        return content.startsWith("[")
                ? getJsonArray(content)
                : getJsonObject(content);
    }

    public static Map getJsonArray(final String contentFile1) {
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
    }
    public static Map getJsonObject(final String contentFile1) {
        JSONObject jsonObject;
        JSONParser jsonParser = new JSONParser();
        try {
            jsonObject = (JSONObject) jsonParser.parse(contentFile1);
            return jsonObject;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public static String convertYamlToJsonData(final String yaml) {
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
    public static Map<String, String> replaceNull(
            final Map parseFileContent) {
        HashMap hashMap = new HashMap();
        Pattern braces = Pattern.compile("\\{.*}");
        Pattern squareBrackets = Pattern.compile("\\[.*]");
        for (Object key : parseFileContent.keySet()) {
            String value;
            parseFileContent.putIfAbsent(key, "null");
            value = parseFileContent.get(key).toString();
            Matcher hasBraces = braces.matcher(value);
            Matcher hasSquareBrackets = squareBrackets.matcher(value);
            if (value == null) {
                hashMap.put(key, "null");
            } else if (hasSquareBrackets.find()) {
                value = value.replaceAll("^\\[", "")
                        .replaceAll("\\]$", "");
                Stream<String> stream = Arrays.stream(value.split(","));
                List<String> list = stream.collect(Collectors.toList());
                list.replaceAll(s -> s.replaceAll("\"", "")
                        .replaceAll("\"", ""));
                hashMap.put(key, list);
            } else if (hasBraces.find()) {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    Map<String, String> map = mapper
                            .readValue(value, Map.class);
                    hashMap.put(key, map);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                hashMap.put(key, parseFileContent.get(key));
            }
        }
        return hashMap;
    }
}
