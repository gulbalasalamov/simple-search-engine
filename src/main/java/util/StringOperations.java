package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Util class for String operations
 */
public class StringOperations {
    public static String cleanseInput(String input) {
        String newStr = input.replaceAll("[, . : ;\"]", "");
        newStr = newStr.replaceAll("\\p{P}", "");
        newStr = newStr.replaceAll("\t", "");
        return newStr;
    }

    public static boolean isDigit(String input) {
        String regex = "(.)*(\\d)(.)*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        boolean isMatched = matcher.matches();
        if (isMatched) {
            return true;
        }
        return false;
    }
}