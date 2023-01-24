package hexlet.code.CheckingDataFormat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataFormatingTest {
    String yamlContent;
    String jsonContent;
    DataFormating dataFormating;
    @BeforeEach
    void setUp() {
        yamlContent = "---\n" +
                "  host: hexlet.io\n" +
                "  timeout: 50\n" +
                "  proxy: 123.234.53.22\n" +
                "  follow: false";
        jsonContent = "{\"host\":\"hexlet.io\",\"timeout\":50,\"proxy\":\"123.234.53.22\",\"follow\":false}";
        dataFormating = new DataFormating();
    }

    @Test
    void convertYamlToJson() {
        assertEquals(jsonContent, dataFormating.convertYamlToJson(yamlContent));
    }
}