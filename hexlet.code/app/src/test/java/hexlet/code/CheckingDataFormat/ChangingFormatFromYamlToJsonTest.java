package hexlet.code.CheckingDataFormat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChangingFormatFromYamlToJsonTest {
    ChangingFormatFromYamlToJson changingFormatFromYamlToJson;
    YamlJSONTest yamlJSONTest;

    @BeforeEach
    void setUp() {
        changingFormatFromYamlToJson = new ChangingFormatFromYamlToJson();
        yamlJSONTest = new YamlJSONTest();
    }

    @Test
    void changeFormat() {
        assertEquals(true, ChangingFormatFromYamlToJson.changeFormat(
                "yamlTest",
                yamlJSONTest.tmpYamlFilePath
        ));
    }
}