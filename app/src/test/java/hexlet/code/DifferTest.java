package hexlet.code;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DifferTest {
    private final String expected = """
                {
                  - host: jd.com
                  + host: tencentcloud.com
                }""";
    private final String testFilePath1 = "testFile1.json";
    private final String testFilePath2 = "testFile2.json";
    private final String testFilePathEmpty = "testFileEmpty.json";


    @BeforeEach
    void setUp() {
        String testFilePath1Content = "[{\"host\": \"jd.com\"}]";
        createNewTestFile(testFilePath1, testFilePath1Content);
        String testFilePath2Content = "{\"host\": \"tencentcloud.com\"}";
        createNewTestFile(testFilePath2, testFilePath2Content);
        String testFilePathEmptyContent = "";
        createNewTestFile(testFilePathEmpty, testFilePathEmptyContent);

    }

    private void createNewTestFile(String testFilePath, String testFilePathContent) {
        try {
            File myObj = new File(testFilePath);
            if (myObj.createNewFile()) {
                try {
                    FileWriter myWriter = new FileWriter(testFilePath);
                    myWriter.write(testFilePathContent);
                    myWriter.close();
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Test
    void generateTwoParams() throws Exception {
        assertEquals(expected, Differ.generate(testFilePath1, testFilePath2));
    }

    @Test
    void generate() throws Exception {
        String testStr;
        testStr = Differ.generate(testFilePath1, testFilePath2, "stylish");
        assertEquals(expected, testStr);
    }
    @Test
    void generateFileEmptyContent() {
        String testStrNull;
        try {
            testStrNull = Differ.generate(testFilePath1, testFilePathEmpty, "stylish");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertEquals("""
                {
                  - host: jd.com
                }""", testStrNull);

    }

    @Test
    void isExist() {
        assertThrows(java.lang.Exception.class, () -> Differ.generate(
                "/home/timur/IdeaProjects/java-project-71/app/src/test/resources/file1.json",
                "notExist.txt"));
    }

    @AfterEach
    void deleteTempFiles() {
        deleteFile(testFilePath1);
        deleteFile(testFilePath2);
        deleteFile(testFilePathEmpty);
    }

    private void deleteFile(String testFilePath) {
        File myObj = new File(testFilePath);
        myObj.delete();
    }
}
