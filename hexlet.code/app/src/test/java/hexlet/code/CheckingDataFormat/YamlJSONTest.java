package hexlet.code.CheckingDataFormat;

public class YamlJSONTest {
    public String yamlContent = "---\n" +
            "  host: hexlet.io\n" +
            "  timeout: 50\n" +
            "  proxy: 123.234.53.22\n" +
            "  follow: false";

    public String jsonContent = "{\"host\":\"hexlet.io\",\"timeout\":50,\"proxy\":\"123.234.53.22\",\"follow\":false}";
}
