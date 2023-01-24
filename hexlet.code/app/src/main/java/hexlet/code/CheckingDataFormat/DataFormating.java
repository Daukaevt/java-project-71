package hexlet.code.CheckingDataFormat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class DataFormating {
    public static String convertYamlToJson(String yaml) {
        ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
        Object obj = null;
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
}
