package hexlet.code;


import picocli.CommandLine;

public class App {
    public static void main(String[] args) {
        new CommandLine(new GeneretingDiffer()).execute(args);
    }
}
//@CommandLine.Command(name="gendiff")
@CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
class GeneretingDiffer implements Runnable {

    @Override
    public void run() {
        System.out.println("Hello, picocli!");
    }
}
