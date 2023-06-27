package hexlet.code;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Parser {
    public static Map parse(final String content, String dataFormat) throws JsonProcessingException, ParseException {
        if (content.isEmpty()) {
            return new HashMap<String, String>();
        }
        boolean validJSON = isJSONValid(content);
        HashMap<Object, Object> unstructuredData = new HashMap<>();
        unstructuredData.put("newKey", content);
        switch (dataFormat) {
            case "json" -> {
                if (validJSON) {
                    return replaceContentToNotNullStringValue(getJson(content));
                } else {
                    return unstructuredData;
                }
            }
            case "yml" -> {
                return replaceContentToNotNullStringValue(getJson(convertYamlToJsonData(content)));
            }
            default -> {
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

    static Map getArrayDataMap(JSONArray jsonArray) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String,Object>> typeRef = new TypeReference<>() {};
        return mapper.readValue(jsonArray.toJSONString().replaceAll("^.|.$", ""), typeRef);
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

    static Map replaceContentToNotNullStringValue(Map json) {
        Map <String, String> jsonInputToMap = new HashMap<>(json);
        return jsonInputToMap
                .entrySet().stream()
                .map(x -> new AbstractMap.SimpleEntry<String, String>(x.getKey(), String.valueOf(x.getValue())))
                .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
    }
}
