package hexlet.code;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

import static hexlet.code.Differ.getDataFormat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {
    private String jsonContent;
    private String expected;
    private String jsonArray;
    private String wrongArray;
    private HashMap<String, Object> expectedData;

    private String filePath;

    @BeforeEach
    void setUp() throws IOException {
        expected = "{id=1, title=hello}";
        jsonArray = """
                [
                                  {
                                    "id":1,
                                    "title":"hello"
                                  }
                                        ]""";
        wrongArray = """
                [
                                 {
                                   "id":"1946-04-14T00:00:01",
                                   "title":"hello"
                                 }""".indent(1);
        filePath = "/home/timur/IdeaProjects"
                + "/java-project-71/app/src/test/resources/nestedFile1.json";
        jsonContent = Files.readString(Path.of(filePath));
        expectedData = new HashMap<>();
        expectedData.put("profile", "{\"name\":\"typicode\"}");
        expectedData.put("posts", "[{\"id\":1,\"title\":\"hello\"}]");
        expectedData.put("field", "null");
    }

    @Test
    void parse() throws IOException, ParseException {
        assertEquals(String.valueOf(expectedData),
                String.valueOf(Parser.parse(jsonContent, getDataFormat(filePath))));
        String wrong = Files.readString(Path.of(
               "/home/timur/IdeaProjects/java-project-71/app/src/test/resources/text.txt"));
        assertEquals("{newKey=Some text...}",
                String.valueOf(Parser.parse(wrong, "json")));
    }

    @Test
    void parseYaml() throws JsonProcessingException, ParseException {
        String testYml = """
                ---
                martin: {name: Martin D'vloper, job: Developer, skill: Elite}
                fruits: ['Apple', 'Orange', 'Strawberry', 'Mango']""";
        HashMap<Object, Object> expectedYml = new HashMap<>();
        expectedYml.put("martin", "{\"skill\":\"Elite\",\"name\":\"Martin D'vloper\",\"job\":\"Developer\"}");
        expectedYml.put("fruits", "[\"Apple\",\"Orange\",\"Strawberry\",\"Mango\"]");
        assertEquals(String.valueOf(expectedYml), String.valueOf(Parser.parse(testYml, "yml")));
    }
    @Test
    void isJSONValid() {
        assertTrue(Parser.isJSONValid(jsonContent));
    }

    @Test
    void isJSONNotValid() {
        assertFalse(Parser.isJSONValid("test"));
    }

    @Test
    void getJson() throws IOException, ParseException {
        String jsonArrayData = Files.readString(Path.of(
                "/home/timur/IdeaProjects/java-project-71/app/src/test/resources/array.json"));
        HashMap<Object, Object> expectedJsonData = new HashMap<>();
        expectedJsonData.put("id", 1);
        expectedJsonData.put("title", "hello");
        assertEquals(String.valueOf(expectedJsonData), String.valueOf(Parser.getJson(jsonArrayData)));

    }

    @Test
    void getJsonArray() throws JsonProcessingException {
        assertEquals(expected, String.valueOf(Parser.parseJsonArray(jsonArray)));
        assertThrows(RuntimeException.class, () -> String.valueOf(Parser.parseJsonArray(wrongArray)));
    }
    @Test
    void getArrayDataMapTest() throws JsonProcessingException {
        JSONParser jsonParser = new JSONParser();
        try {
            assertEquals(expected, String.valueOf(Parser.getArrayDataMap(
                    (JSONArray) jsonParser.parse(jsonArray))));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        assertThrows(ParseException.class, () -> String.valueOf(
                Parser.getArrayDataMap((JSONArray) jsonParser.parse(wrongArray))));
    }


    @Test
    void getJsonObject() throws ParseException {
        String expectedJson = "{\"obj1\":{\"nestedKey\":\"value\",\"isNested\":true}}";
        assertEquals(expectedJson, String.valueOf(Parser.parseJsonObject("""
                {"obj1": {
                    "nestedKey": "value",
                    "isNested": true
                  }}""")));
    }

    @Test
    void convertYamlToJsonData() throws JsonProcessingException {
        String expectedYamlToJson = "{\"martin\":{\"name\":\"Martin D'vloper\","
                + "\"job\":\"Developer\",\"skill\":\"Elite\"}}";
        assertEquals(expectedYamlToJson, Parser.convertYamlToJsonData("---\n"
                + "martin: {name: Martin D'vloper, job: Developer, skill: Elite}"));
    }

    @Test
    void replaceNull() throws ParseException {
        assertEquals(String.valueOf(expectedData),
                String.valueOf(Parser.replaceContentToNotNullStringValue(Parser.parseJsonObject(jsonContent))));
    }
}
