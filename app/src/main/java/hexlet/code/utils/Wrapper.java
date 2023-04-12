package hexlet.code.utils;

public class Wrapper {
    public Wrapper(String value1, String value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public String getValue1() { return this.value1; }
    public String getValue2() { return this.value2; }

    private String value1;
    private String value2;

}
