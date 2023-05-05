package hexlet.code;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ReaderTest {

    private final String testFilePath1
            = "/home/timur/IdeaProjects/java-project-71/app/src/test/resources/nestedFile1.json";
    private final String fileContent = Reader.readFile(testFilePath1);

    @Test
    void readFile() {
        String expected = """
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
        assertEquals(expected, fileContent);
    }

    @Test
    void readFileThrowException() {
        assertThrows(RuntimeException.class, () -> Reader.readFile("wrongPath"));
    }
}
