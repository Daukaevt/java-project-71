package hexlet.code;

import hexlet.code.data.DataFormating;
import hexlet.code.data.JSONUtils;
import hexlet.code.data.ReadFile;

import java.util.Map;

public class Differ {
    public static String generate(
            String stylish) throws Exception {
        var parseFile1Content = parsingInit("/home/timur/IdeaProjects/java-project-71/app/src/test/resources/file1.json");
        Map file1Content = CreatingNotNullContent.replaceNull(parseFile1Content);
        var parseFile2Content = parsingInit("/home/timur/IdeaProjects/java-project-71/app/src/test/resources/file2.json");
        Map file2Content = CreatingNotNullContent.replaceNull(parseFile2Content);
        var comparedDatas = DataAggregating
                .agregate(file1Content, file2Content);
        var checkDataForNull = CheckingForNull.check(comparedDatas);
        var result = SortingDataString.sort(checkDataForNull);
     return result;
    }
    //move to new class and rename
    public static Map parsingInit(final String filePath) {
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

            parseJson1 = hexlet.code.JacksonObjectMapperMakingHostAddresses
                    .getData(String.valueOf(content));
                    //JacksonObjectMapperMakingHostAddresses
                    //.parse(String.valueOf(content));
        } else {
            //yaml или нет
            String jsonContent = DataFormating.convertYamlToJson(content);
            try {
                parseJson1 = JacksonObjectMapperMakingHostAddresses.getData(String.valueOf(content));
            }  catch (Exception e) {
                // все остальное
                System.out.println("Wrong file format"); //+ e.getMessage());
                System.exit(0);
            }
        }
        return parseJson1;
    }
}
