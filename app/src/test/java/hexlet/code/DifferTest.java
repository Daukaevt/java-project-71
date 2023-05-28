package hexlet.code;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DifferTest {
    private final String expected = """
                {
                  - host: jd.com
                  + host: tencentcloud.com
                }""";
    private final String testFilePath1 = "testFile1.txt";
    private final String testFilePath2 = "testFile2.txt";
    private final String testFilePathEmpty = "testFileEmpty.txt";


    @BeforeEach
    void setUp() {
        String testFilePath1Content = "{\"host\": \"jd.com\"}";
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
        testStr = Differ.generate(testFilePath1, testFilePath2, "styish");
        assertEquals(expected, testStr);
    }
    @Test
    void generateFileNullContent() {
        String testStr;
        try {
            testStr = Differ.generate(testFilePath1, testFilePath2, "styish");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertNotNull(testStr);
    }
    @Test
    void generateFileEmptyContent() {
        String testStrNull;
        try {
            testStrNull = Differ.generate(testFilePath1, testFilePathEmpty, "styish");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertEquals("""
                {
                  - host: jd.com
                }""", testStrNull);

    }

    @Test
    void isNotExist() throws Exception {
        assertEquals("File does not exist", Differ.generate(
                "/home/timur/IdeaProjects/java-project-71/app/src/test/resources/file1new.json",
                "not exist file"));
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
