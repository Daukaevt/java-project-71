package hexlet.code;


import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

@Command(
        name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "1.0",
        description = "Compares two configuration files and shows a difference."
)
public class App implements Runnable{
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
            names = {"-f", "--format"},
            description = "output format [default: stylish]"
    )
    private boolean format = false;
    

    public static void main(String[] args) {
       new CommandLine(new App()).execute("-h");
    }

    @Override
    public void run() {
        System.out.println("HW!" + filePath1 + filePath2 + format);
    }
}

