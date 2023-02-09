package hexlet.code.CheckingDataFormat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataFormatingTest {
    String yamlContent;
    String jsonContent;
    DataFormating dataFormating;
    YamlJSONTest yamlJSONTest;
    @BeforeEach
    void setUp() {
        dataFormating = new DataFormating();
        yamlJSONTest = new YamlJSONTest();

    }

    @Test
    void convertYamlToJson() {
        assertEquals(yamlJSONTest.jsonContent, dataFormating.convertYamlToJson(yamlJSONTest.yamlContent));
    }
}