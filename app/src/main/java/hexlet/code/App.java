package hexlet.code;


import hexlet.code.formatters.FormattingJson;
import hexlet.code.formatters.FormattingPlain;
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
        System.out.println(format + " st");
        String content = null;
        try {
            content = Differ.generate(filePath1, filePath2, format);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (format != null) {
            if (format.toString() == "plain") {
                content = FormattingPlain.format(content);
            } else if (format.toString() == "json") {
                content = FormattingJson.format(content);
            }
        }
            System.out.println(content);
        return null;
    }
}

