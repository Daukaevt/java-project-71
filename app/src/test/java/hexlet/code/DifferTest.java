package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    private Differ differ = new Differ();
    private String testFilePath1 = "testFile1.txt";
    private String testFilePath1Content = "{\"host\": \"jd.com\"}";
    private String testFilePath2 = "testFile2.txt";
    private String testFilePath2Content = "{\"host\": \"tencentcloud.com\"}";
    // add format changing
    private String format;



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
        String testStr;
        try {
            testStr = differ.generate(testFilePath1, testFilePath2, "styish");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertEquals("""
                {
                  - host: jd.com
                  + host: tencentcloud.com
                }""", testStr);
        deleteFile(testFilePath1);
        deleteFile(testFilePath2);

    }

    private void deleteFile(String testFilePath) {
        File myObj = new File(testFilePath);
        myObj.delete();
    }
}
