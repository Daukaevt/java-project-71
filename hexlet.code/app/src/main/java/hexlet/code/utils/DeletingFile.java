package hexlet.code.utils;

import java.io.File;

public class DeletingFile {
    public static void delete(String s) {
        File myObj = new File(s);
        myObj.delete();
    }
}
