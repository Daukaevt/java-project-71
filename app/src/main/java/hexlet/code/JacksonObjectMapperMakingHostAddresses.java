package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class JacksonObjectMapperMakingHostAddresses {
    public static Map getData(final String contentFile1) {
        Map<String, Object> map;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            map = objectMapper.readValue(contentFile1, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return map;
    }
}
