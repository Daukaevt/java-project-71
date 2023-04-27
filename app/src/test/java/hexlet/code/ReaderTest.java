package hexlet.code;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


class ReaderTest {

    final private String testFilePath1
            = "/home/timur/IdeaProjects/java-project-71/app/src/test/resources/nestedFile1.json";
    String fileContent = Reader.readFile(testFilePath1);
    String testContent = """
            {
              "posts": [
                {
                  "id": 1,
                  "title": "hello"
                }
              ],
              "profile": {
                "name": "typicode"
              }
            }""";
    @Test
    void readFile() {
        assertEquals(testContent, fileContent);
    }
}