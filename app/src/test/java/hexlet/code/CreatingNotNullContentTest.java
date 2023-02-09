package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreatingNotNullContentTest {
    CreatingNotNullContent creatingNotNullContent;
    Differ differ;

    @BeforeEach
    void setUp() {
        creatingNotNullContent = new CreatingNotNullContent();
        differ = new Differ();
    }

//    @Test
//    void replaceNull() {
//        var testJson = differ.parsingInit("test.yaml");
//        assertEquals("{test=test, test1=null}", creatingNotNullContent.replaceNull(testJson, format).toString());
//    }
}