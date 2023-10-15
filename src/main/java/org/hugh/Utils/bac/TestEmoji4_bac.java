package org.hugh.Utils.bac;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author adward
 * @date 2023/10/11 16:16
 */
public class TestEmoji4_bac {

    public static void main(String[] args) {
        String textWithUnicode = "Hello \\uD83D\\uDE00, how are you? \\uD83D\\uDC4D";
        // 正则表达式用于匹配 Unicode 编码
        String emojiRegex = "\\\\u[0-9A-Fa-f]{4}";

        Pattern pattern = Pattern.compile(emojiRegex);
        Matcher matcher = pattern.matcher(textWithUnicode);
        // 使用 StringBuffer
        StringBuffer restoredText = new StringBuffer();

        while (matcher.find()) {
            String unicode = matcher.group();
            String emoji = fromUnicode(unicode);
            // 使用 StringBuffer
            matcher.appendReplacement(restoredText, emoji);
        }
        // 使用 StringBuffer
        matcher.appendTail(restoredText);

        System.out.println("Original Text: " + textWithUnicode);
        System.out.println("Restored Text: " + restoredText.toString());
    }

    public static String fromUnicode(String unicode) {
        StringBuilder emoji = new StringBuilder();
        String[] hexValues = unicode.split("\\\\u");
        for (String hex : hexValues) {
            // 避免空字符串引起的 NumberFormatException
            if (!hex.isEmpty()) {
                int intValue = Integer.parseInt(hex, 16);
                emoji.append((char) intValue);
            }
        }
        return emoji.toString();
    }

}
