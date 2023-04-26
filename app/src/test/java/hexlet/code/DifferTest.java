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
    final private Differ differ = new Differ();
    final private String testFilePath1 = "testFile1.txt";
    final private String testFilePath2 = "testFile2.txt";
    final private String testFilePathEmpty = "testFileEmpty.txt";


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
    void generate() {
        String testStr;
        try {
            testStr = Differ.generate(testFilePath1, testFilePath2, "styish");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertEquals("""
                {
                  - host: jd.com
                  + host: tencentcloud.com
                }""", testStr);
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
