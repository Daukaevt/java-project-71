package hexlet.code;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

class AppTest {


    @Test
    public void testMain() throws IOException {
        System.out.println("main");
        String[] args = null;
        final InputStream original = System.in;
        final FileInputStream fips = new FileInputStream(
                "/home/timur/IdeaProjects/java-project-71/app/src/test/resources/file1.json");
        System.setIn(fips);
        App.main(args);
        System.setIn(original);
    }

    @Test
    void testCall() {
        Assertions.assertEquals(0, new CommandLine(new App()).execute(
                "/home/timur/IdeaProjects/java-project-71/app/src/test/resources/file1.yml",
                "/home/timur/IdeaProjects/java-project-71/app/src/test/resources/file2.yml"));
        Assertions.assertEquals(1, new CommandLine(new App()).execute(
                "/home/timur/IdeaProjects/java-project-71/app/src/test/resources/file1.yml",
                "notExist.txt"));
    }
}
