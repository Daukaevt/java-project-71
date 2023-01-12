package hexlet.code;

import hexlet.code.data.JacksonObjectMapperMakingHostAddresses;
import hexlet.code.data.ReadFile;

public class Differ {
    public static String generate(String filePath1, String filePath2) {
        String contentFile1 = null;
        try {
            contentFile1 = ReadFile.read(filePath1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        var parseJson1 = JacksonObjectMapperMakingHostAddresses.parse(contentFile1);
        String contentFile2 = null;
        try {
            contentFile2 = ReadFile.read(filePath2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        var parseJson2 = JacksonObjectMapperMakingHostAddresses.parse(contentFile2);
        var comparedDatas = ComparingMaps.compare(parseJson1, parseJson2);
        var checkDataForNull = CheckingForNull.check(comparedDatas);
        var dataString = CreatingDataString.create(checkDataForNull);
        return dataString;
    }
}
