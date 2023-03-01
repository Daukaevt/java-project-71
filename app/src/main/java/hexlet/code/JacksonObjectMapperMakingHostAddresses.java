package hexlet.code;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@JsonIgnoreProperties(ignoreUnknown = true)
public class JacksonObjectMapperMakingHostAddresses {

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
