package hexlet.code;

import hexlet.code.data.DataFormating;
import hexlet.code.data.JSONUtils;
import hexlet.code.data.JacksonObjectMapperMakingHostAddresses;
import hexlet.code.data.ReadFile;

import java.io.IOException;
import java.util.Map;

public class Differ {
    public static String generate(
            final String filePath1,
            final String filePath2
    ) {
        var parseFile1Content = parsingInit(filePath1);
        var parseFile2Content = parsingInit(filePath2);
        var comparedDatas = ComparingMaps.compare(
                (Map) parseFile1Content,
                (Map) parseFile2Content
        );
        var checkDataForNull = CheckingForNull.check(comparedDatas);
        return CreatingDataString.create(checkDataForNull);
    }
    //move to new class and rename
    private static Object parsingInit(final String filePath) {
        String content;
        // строка из файла
        try {
            content = ReadFile.read(filePath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // строка json или нет
        boolean validJSON = JSONUtils.isJSONValid(content);
        Map parseJson1 = null;
        if (validJSON) {
            parseJson1 = JacksonObjectMapperMakingHostAddresses
                    .parse(String.valueOf(content));
        } else {
            //yaml или нет
            String jsonContent = DataFormating.convertYamlToJson(content);
            try {
                parseJson1 = JacksonObjectMapperMakingHostAddresses
                        .parse(jsonContent);
            }  catch (Exception e) {
                // все остальное
                System.out.println("Wrong file format"); //+ e.getMessage());
                System.exit(0);
            }
        }
        return parseJson1;
    }
}
