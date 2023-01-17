package hexlet.code.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ReadFileTest {
    ReadFile readFile;

    @BeforeEach
    void setUp() {
        readFile = new ReadFile();
        try {
            File myObj = new File("tmp.txt");
            if (myObj.createNewFile()) {
                try {
                    FileWriter myWriter = new FileWriter("tmp.txt");
                    myWriter.write("Some text");
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
    void read() {
        try {
            assertNotNull(readFile.read("tmp.txt"));
            File myObj = new File("tmp.txt");
            myObj.delete();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}