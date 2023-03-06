package hexlet.code.formatters;

import hexlet.code.MakingStyle;

import java.util.ArrayList;

public class FormattingPlain {
    public static String format(String result) {
         var tmpText = result.replaceAll("^.|.$", "");
        var tmpLines = tmpText.lines();
        String secondValue = "";
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<String> tmpArrayList = new ArrayList<>();
        tmpLines.forEach(arrayList::add);
        arrayList.remove(0);
        for (int i = 0; i < arrayList.size(); i++) {
            String tmpSubstringPlus;
            String line = arrayList.get(i);
            var key = line.substring(4).replaceAll(":\s.*", "");
            String firstValue = MakingStyle.makeStyle(line.replaceAll(".*:\s", ""));
            if (i + 1 < arrayList.size()) {
                secondValue  = MakingStyle.makeStyle(arrayList.get(i + 1)
                        .replaceAll(".*:\s", ""));
            }
            switch (line.charAt(2)) {
                case '-' -> {
                    if (i + 1 < arrayList.size()) {
                        if (arrayList.get(i + 1).contains(key)) {
                            String tempStrMinusPlus = "Property '" + key + "' was updated. From "
                                    + firstValue + " to " + secondValue;
                            tmpArrayList.add(tempStrMinusPlus);
                            i++;
                        } else {
                            String tempStrMinus = "Property '" + key + "' was removed";
                            tmpArrayList.add(tempStrMinus);
                        }
                    } else {
                        String tempStrMinus = "Property '" + key + "' was removed";
                        tmpArrayList.add(tempStrMinus);
                    }
                }
                case '+' -> {
                    tmpSubstringPlus = "Property '" + key + "' was added with value: " + firstValue;
                    tmpArrayList.add(tmpSubstringPlus);
                }
            }
        }
        return createResult(tmpArrayList);
    }
    private static String createResult(ArrayList<String> tmpArrayList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String line: tmpArrayList) {
            stringBuilder.append(line).append("\n");
        }
        return stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), "").toString();
    }
}
