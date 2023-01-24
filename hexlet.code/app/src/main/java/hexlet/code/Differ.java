package hexlet.code;

import hexlet.code.CheckingDataFormat.FileContentFormatToJSON;
import hexlet.code.data.JSONUtils;
import hexlet.code.data.JacksonObjectMapperMakingHostAddresses;
import hexlet.code.data.ReadFile;
import hexlet.code.utils.DeletingFile;

import java.util.Map;

public class Differ {
    public static String generate(
            final String filePath1,
            final String filePath2
    ) {
        var pathToJsonTempFile1
                = FileContentFormatToJSON.factory(filePath1, "tmpFile1.yaml");
        var pathToJsonTempFile2
                = FileContentFormatToJSON.factory(filePath2, "tmpFile2.yaml");
        var parseFile1Content = parsingInit(pathToJsonTempFile1); //(filePath1);
        var parseFile2Content = parsingInit(pathToJsonTempFile2); //(filePath2);
        DeletingFile.delete("tmpFile1.yaml");
        DeletingFile.delete("tmpFile2.yaml");
        var comparedDatas = ComparingMaps.compare(
                (Map) parseFile1Content,
                (Map) parseFile2Content
        );
        var checkDataForNull = CheckingForNull.check(comparedDatas);
        return CreatingDataString.create(checkDataForNull);
    }

    private static Object parsingInit(final String filePath) {
        String content;
        try {
            content = ReadFile.read(filePath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        boolean validJSON = JSONUtils.isJSONValid(content);
        Map parseJson1;
        if (validJSON) {
            parseJson1 = JacksonObjectMapperMakingHostAddresses
                    .parse(String.valueOf(content));
        } else {
            return "JSON file is no valid";
        }
        return parseJson1;
    }
}
