package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    Differ differ = new Differ();
    String testFilePath1 = "testFile1.txt";
    String testFilePath1Content = "{\"host\": \"jd.com\"}";
    String testFilePath2 = "testFile2.txt";
    String testFilePath2Content = "{\"host\": \"tencentcloud.com\"}";
    // add format changing
    String format;



    @BeforeEach
    void setUp() {
        createNewTestFile(testFilePath1, testFilePath1Content);
        createNewTestFile(testFilePath2, testFilePath2Content);
        format = "plain";

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
        String testStr = null;
        try {
            testStr = differ.generate(format, testFilePath1, format);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertEquals("{\n" +
                "- host: jd.com\n" +
                "+ host: tencentcloud.com\n" +
                "}", testStr);
        deleteFile(testFilePath1);
        deleteFile(testFilePath2);

    }

    private void deleteFile(String testFilePath) {
        File myObj = new File(testFilePath);
        myObj.delete();
    }
}