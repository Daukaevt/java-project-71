package hexlet.code.utils;

public final class Wrapper {
    public Wrapper(final String value1, final String value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public String getValue1() {
        return this.value1; }
    public String getValue2() {
        return this.value2; }

    private final String value1;
    private final String value2;

}
