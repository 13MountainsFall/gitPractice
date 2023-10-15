package org.hugh.Utils;

import com.sun.tools.javac.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author adward
 * @date 2023/10/11 16:29
 */
public class EmojiTransUtil_Bac {

    public static boolean ifWithEmoji(String text) {
        // 正则表达式用于匹配表情符号以及苹果专属标记标点
        String emojiRegex = "\\p{So}|\\p{P}";
        Pattern pattern = Pattern.compile(emojiRegex);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return true;
        }
        return false;
    }

    public static boolean ifWithUnicode(String text) {
        // 正则表达式用于匹配 Unicode 编码
        String emojiRegex = "\\\\u[0-9A-Fa-f]{4}";
        Pattern pattern = Pattern.compile(emojiRegex);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return true;
        }
        return false;
    }


    public static String fromWithEmoji(String textWithEmojis) {
        // 正则表达式用于匹配表情符号
        String emojiRegex = "\\p{So}|\\p{P}";
        Pattern pattern = Pattern.compile(emojiRegex);
        Matcher matcher = pattern.matcher(textWithEmojis);

        StringBuffer modifiedText = new StringBuffer();
        while (matcher.find()) {
            String emoji = matcher.group();
            String unicode = toUnicode(emoji);
            matcher.appendReplacement(modifiedText, unicode);
        }
        matcher.appendTail(modifiedText);

        System.out.println("Original Text: " + textWithEmojis);
        System.out.println("Modified Text: " + modifiedText.toString());
        return String.valueOf(modifiedText);
    }


    public static String toWithEmoji(String textWithUnicode) {
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

        return String.valueOf(restoredText);
    }

    private static String toUnicode(String input) {
        StringBuilder unicode = new StringBuilder();
        for (char character : input.toCharArray()) {
            unicode.append("\\\\u").append(Integer.toHexString(character | 0x10000).substring(1));
        }
        return unicode.toString();
    }

    private static String fromUnicode(String unicode) {
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
