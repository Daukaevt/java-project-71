package hexlet.code.data;

import hexlet.code.utils.DeletingFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ReadFileTest {
    String tmpFileName;

    @BeforeEach
    void setUp() {
        tmpFileName= "tmp.txt";
        try {
            File myObj = new File(tmpFileName);
            if (myObj.createNewFile()) {
                try {
                    FileWriter myWriter = new FileWriter(tmpFileName);
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
            assertNotNull(ReadFile.read(tmpFileName));
            DeletingFile.delete(tmpFileName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}