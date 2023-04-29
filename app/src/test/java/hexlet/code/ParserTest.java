package hexlet.code;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {
    String jsonContent;
    HashMap<String, Object> expectedData;

    @BeforeEach
    void setUp() {
        jsonContent = Reader.readFile("/home/timur/IdeaProjects"
                + "/java-project-71/app/src/test/resources/nestedFile1.json");
        expectedData = new HashMap<>();
        expectedData.put("profile", "{name=typicode}");
        expectedData.put("posts", "[{id:1, title:hello}]");
        expectedData.put("field", "null");
    }

    @Test
    void parse() {
        assertEquals(String.valueOf(expectedData),
                String.valueOf(Parser.parse(jsonContent)));
    }

    @Test
    void isJSONValid() {
        assertTrue(Parser.isJSONValid(jsonContent));
    }

    @Test
    void getJsonArray() {
        String expected = "{id=1, title=hello}";
                assertEquals(expected, String.valueOf(Parser.getJsonArray("""
                        [
                          {
                            "id":1,
                            "title":"hello"
                          }
                        ]""")));
    }

    @Test
    void getJsonObject() {
        String expected = "{\"obj1\":{\"nestedKey\":\"value\",\"isNested\":true}}";
        assertEquals(expected, String.valueOf(Parser.getJsonObject("""
                {"obj1": {
                    "nestedKey": "value",
                    "isNested": true
                  }}""")));
    }

    @Test
    void convertYamlToJsonData() {
        String expected = "{\"martin\":{\"name\":\"Martin D'vloper\","
                + "\"job\":\"Developer\",\"skill\":\"Elite\"}}";
        assertEquals(expected, Parser.convertYamlToJsonData("---\n" +
                "martin: {name: Martin D'vloper, job: Developer, skill: Elite}"));
    }

    @Test
    void replaceNull() {
        assertEquals(String.valueOf(expectedData),
                String.valueOf(Parser.replaceNull(Parser.getJsonObject(jsonContent))));
    }
}