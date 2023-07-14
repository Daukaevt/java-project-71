package hexlet.code;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static String convertYamlToJson(final String yaml) throws JsonProcessingException {
        if (yaml.isEmpty()) return "";
        ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
        Object obj;
        obj = yamlReader.readValue(yaml, Object.class);
        ObjectMapper jsonWriter = new ObjectMapper();
        return jsonWriter.writeValueAsString(obj);
    }
    public static String convertJsonToYaml(String jsonString) throws IOException {
        JsonNode jsonNodeTree = new ObjectMapper().readTree(jsonString);
        return new YAMLMapper().writeValueAsString(jsonNodeTree);
    }
    private static String replaceSquareBrackets (String content) {
        return content.replaceAll("^\\[", "").replaceAll("]$", "");
    }
    private static Map parseYaml(String content) throws Exception  {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(convertJsonToYaml(replaceSquareBrackets(convertYamlToJson(content))), Map.class);
    }

    private static Map parseJson(String content) throws Exception  {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(replaceSquareBrackets(content), Map.class);
    }
    public static Map parse(String content, String dataFormat) throws Exception {
        if (content.isEmpty()) {
            return new HashMap();
        }
        return switch (dataFormat) {
            case "yml", "yaml" -> parseYaml(content);
            case "json" -> parseJson(content);
            default -> throw new Exception("Unknown format: '" + dataFormat + "'");
        };
    }
}


