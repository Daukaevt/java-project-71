package hexlet.code;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@JsonIgnoreProperties(ignoreUnknown = true)
public class JacksonObjectMapperMakingHostAddresses {



//        YAMLFactory factory = new YAMLFactory();
//        JsonParser parser = null; // don't be fooled by method name...
//        try {
//            parser = factory.createParser(contentFile1);
//            System.out.println(parser + " parser");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        while (true) {
//            try {
//                if (!(parser.nextToken() != null)) break;
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//
//            try {
//                System.out.println(parser.getText());
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        return new HashMap();
//    }






//    public static Map getData1(final String contentFile1) {
//        Map<Object, Object> map;
//        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
//        try {
//            map = objectMapper.readValue(contentFile1, new TypeReference<>() {
//            });
//            System.out.println(map + "   -------    json map");
//        } catch (JsonProcessingException e) {
//
//            System.out.println("ERROR " + e);
//            throw new RuntimeException(e);
//        }
//        return map;
//    }
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
