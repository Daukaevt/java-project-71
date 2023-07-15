package hexlet.code;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {

    @Test
    void parse() {
        String filePath = "/home/timur/IdeaProjects"
                + "/java-project-71/app/src/test/resources/nestedFile1.json";
        String jsonContent = """
                {
                  "posts": [
                    {
                      "id": 1,
                      "title": "hello"
                    }
                  ],
                  "profile": {
                    "name": "typicode"
                  },
                  "field": null
                }""";
        String expected = "{posts=[{id=1, title=hello}], profile={name=typicode}, field=null}";
        try {
            assertEquals(expected, String.valueOf(Parser.parse(jsonContent, "json")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertThrows(java.lang.Exception.class, () -> {
            Parser.parse(jsonContent,
                    "txt");
        });
    }

    @Test
    void parseYaml() {
        String testYml = """
                ---
                martin: {name: Martin D'vloper, job: Developer, skill: Elite}
                fruits: ['Apple', 'Orange', 'Strawberry', 'Mango']""";
        String expected = "{martin={name=Martin D'vloper, job=Developer, skill=Elite},"
                + " fruits=[Apple, Orange, Strawberry, Mango]}";
        try {
            assertEquals(expected, String.valueOf(Parser.parse(testYml, "yml")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void convertYamlToJsonData() throws JsonProcessingException {
        String expectedYamlToJson = "{\"martin\":{\"name\":\"Martin D'vloper\","
                + "\"job\":\"Developer\",\"skill\":\"Elite\"}}";
        assertEquals(expectedYamlToJson, Parser.convertYamlToJson("---\n"
                + "martin: {name: Martin D'vloper, job: Developer, skill: Elite}"));
    }
}
