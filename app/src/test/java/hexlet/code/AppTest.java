package hexlet.code;

import com.puppycrawl.tools.checkstyle.gui.Main;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {


    @Test
    public void testMain() throws IOException {
        System.out.println("main");
        String[] args = null;
        final InputStream original = System.in;
        final FileInputStream fips = new FileInputStream(new File(
                "/home/timur/IdeaProjects/java-project-71/app/src/test/resources/file1.json"));
        System.setIn(fips);
        App.main(args);
        System.setIn(original);
    }

    @Test
    void testCall() {
        assertEquals(0, new CommandLine(new App()).execute(
                "/home/timur/IdeaProjects/java-project-71/app/src/test/resources/file1.yml",
                "/home/timur/IdeaProjects/java-project-71/app/src/test/resources/file2.yml"));
        assertEquals(1, new CommandLine(new App()).execute(
                "/home/timur/IdeaProjects/java-project-71/app/src/test/resources/file1.yml",
                "notExist.txt"));
    }
}