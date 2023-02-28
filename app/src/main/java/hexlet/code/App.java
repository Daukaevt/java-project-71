package hexlet.code;


import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(
        name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "1.0 beta",
        description = "Compares two configuration files and shows a difference."
)
public class App implements Callable {
    @Parameters(
            paramLabel = "filepath1",
            index = "0",
            description = "path to first file"
    )
    private String filePath1;
    @Parameters(
            paramLabel = "filepath2",
            index = "1",
            description = "path to second file"
    )
    private String filePath2;

    @Option(
            names = { "-v", "--version" },
            versionHelp = true,
            description = "Print version information and exit.")
    private boolean versionRequested = false;

    @Option(
            names = {"-f", "--format"},
            description = "output format [default: stylish]",
            paramLabel = "format"//,
           // defaultValue = "stylish"
    )
    private String format = null;

    @Option(
            names = { "-h", "--help" },
            usageHelp = true,
            description = "Show this help message and exit."
    )
    private boolean helpRequested = false;

    public App() {
    }
    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);
    }
    public Object call() throws Exception {
        String content = null;
        if (format == null || format.equals("")) {
            content = generateStylish(filePath1, filePath2);
        } else {
            content = Differ.generate(filePath1, filePath2, format);
        }
        System.out.println(content);
        return null;
    }
    public static String generateStylish (String firstFilePath, String secondFilePath) throws Exception {
        return Differ.generate(firstFilePath, secondFilePath, "stylish");
    }
}

