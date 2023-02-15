package hexlet.code;


import hexlet.code.formatters.FormattingJson;
import hexlet.code.formatters.FormattingPlain;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

@Command(
        name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "1.0 beta",
        description = "Compares two configuration files and shows a difference."
)
public class App implements Runnable {
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
            description = "output format [default: stylish]"
    )
    private String format = "";

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

    @Override
    public void run() {
            String content = Differ.generate(filePath1, filePath2);
        if (format.equals("plain")) {
            content = FormattingPlain.format(content);
        } else if (format.equals("json")) {
            content = FormattingJson.format(content);
        }

            System.out.println(content);
    }
}

