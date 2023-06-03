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
    public static Map parse(final String content, String dataFormat) throws JsonProcessingException, ParseException {
        if (content.isEmpty()) {
            return new HashMap<String, String>();
        }
        boolean validJSON = isJSONValid(content);
        HashMap<Object, Object> unstructuredData = new HashMap<>();
        unstructuredData.put("newKey", content);
        switch (dataFormat) {
            case "json": {
                if (validJSON) {
                    return replaceNull(getJson(content));
                } else {
                    return unstructuredData;
                }
            }
            case "yaml":
                try {
                    return replaceNull(getJson(convertYamlToJsonData(content)));
                } catch (Exception e) {
                    return unstructuredData;
                }
            default: {
                return unstructuredData;
            }
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

    public static Map getJson(String content) throws JsonProcessingException, ParseException {
        return content.startsWith("[")
                ? parseJsonArray(content)
                : parseJsonObject(content);
    }

    public static Map parseJsonArray(final String contentFile1) throws JsonProcessingException {
        JSONArray jsonArray;
        JSONParser jsonParser = new JSONParser();
        try {
            jsonArray = (JSONArray) jsonParser.parse(contentFile1);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return getArrayDataMap(jsonArray);
    }

    public static Map getArrayDataMap(JSONArray jsonArray) throws JsonProcessingException {
        HashMap dataMap = new HashMap();
        for (Object o : jsonArray) {
            ObjectMapper mapper = new ObjectMapper();
            Map contentMapping;
            contentMapping = mapper.readValue(String.valueOf(o), Map.class);
            for (Object key : contentMapping.keySet()) {
                dataMap.put(key, contentMapping.get(key));
            }
        }
        return dataMap;
    }

    public static Map parseJsonObject(final String contentFile1) throws ParseException {
        JSONObject jsonObject;
        JSONParser jsonParser = new JSONParser();
        jsonObject = (JSONObject) jsonParser.parse(contentFile1);
        return jsonObject;
    }
    public static String convertYamlToJsonData(final String yaml) throws JsonProcessingException {
        ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
        Object obj;
        obj = yamlReader.readValue(yaml, Object.class);
        ObjectMapper jsonWriter = new ObjectMapper();
        return jsonWriter.writeValueAsString(obj);
    }

    public static HashMap replaceNull(
            final Map parseFileContent) throws JsonProcessingException {
        HashMap hashMap = new HashMap();
        Pattern braces = Pattern.compile("\\{.*}");
        Pattern squareBrackets = Pattern.compile("\\[.*]");
        for (Object key : parseFileContent.keySet()) {
            String value;
            parseFileContent.putIfAbsent(key, "null");
            value = parseFileContent.get(key).toString();
            Matcher hasBraces = braces.matcher(value);
            Matcher hasSquareBrackets = squareBrackets.matcher(value);
            if (hasSquareBrackets.find()) {
                value = value.replaceAll("^\\[", "").replaceAll("]$", "");
                Stream<String> stream = Arrays.stream(value.split(","));
                List<String> list = stream.collect(Collectors.toList());
                list.replaceAll(s -> s.replaceAll("\"", "").replaceAll("\"", ""));
                hashMap.put(key, list);
            } else if (hasBraces.find()) {
                ObjectMapper mapper = new ObjectMapper();
                Map map = mapper.readValue(value, Map.class);
                hashMap.put(key, map);
            } else {
                hashMap.put(key, parseFileContent.get(key));
            }
        }
        return hashMap;
    }
}
