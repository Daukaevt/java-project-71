package hexlet.code;

import hexlet.code.data.JSONUtils;
import hexlet.code.data.JacksonObjectMapperMakingHostAddresses;
import hexlet.code.data.ReadFile;

import java.util.Map;

public class Differ {
    public static String generate(String filePath1, String filePath2) {
        var parseFile1Content = parsingInit(filePath1);
        var parseFile2Content = parsingInit(filePath2);
        var comparedDatas = ComparingMaps.compare((Map) parseFile1Content, (Map) parseFile2Content);
        var checkDataForNull = CheckingForNull.check(comparedDatas);
        return CreatingDataString.create(checkDataForNull);
    }

    private static Object parsingInit(String filePath1) {
        String content;
        try {
            content = ReadFile.read(filePath1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        boolean validJSON = JSONUtils.isJSONValid(content);
        Map parseJson1;
        if (validJSON) {
            parseJson1 = JacksonObjectMapperMakingHostAddresses.parse(String.valueOf(content));
        } else {
            return "JSON file is no valid";
        }
        return parseJson1;
    }
}
