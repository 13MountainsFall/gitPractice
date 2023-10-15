package org.hugh.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmojiTransUtil {

    private static final String EMOJI_PATTERN = "\\p{So}|\\p{P}";
    private static final String UNICODE_PATTERN = "\\\\u[0-9A-Fa-f]{4}";

    /**
     * 判断是否包含表情符号或苹果专属标记标点
     *
     * @param text 要检查的文本
     * @return 如果包含表情符号或苹果专属标记标点，返回true；否则返回false
     */
    public static boolean ifContainsEmoji(String text) {
        Pattern pattern = Pattern.compile(EMOJI_PATTERN);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }

    /**
     * 判断是否包含Unicode编码
     *
     * @param text 要检查的文本
     * @return 如果包含Unicode编码，返回true；否则返回false
     */
    public static boolean ifContainsUnicode(String text) {
        Pattern pattern = Pattern.compile(UNICODE_PATTERN);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }

    /**
     * 将包含表情符号或苹果专属标记标点的字符串中的特殊字符转为Unicode编码
     *
     * @param textWithEmojis 要处理的文本
     * @return 转换后的文本
     */
    public static String fromWithEmoji(String textWithEmojis) {
        Pattern pattern = Pattern.compile(EMOJI_PATTERN);
        Matcher matcher = pattern.matcher(textWithEmojis);
        StringBuffer modifiedText = new StringBuffer();
        while (matcher.find()) {
            String emoji = matcher.group();
            String unicode = toUnicode(emoji);
            matcher.appendReplacement(modifiedText, unicode);
        }
        matcher.appendTail(modifiedText);
        return modifiedText.toString();
    }

    /**
     * 将包含Unicode编码的字符串中的Unicode编码还原为特殊字符
     *
     * @param textWithUnicode 包含Unicode编码的文本
     * @return 还原后的文本
     */
    public static String toWithEmoji(String textWithUnicode) {
        Pattern pattern = Pattern.compile(UNICODE_PATTERN);
        Matcher matcher = pattern.matcher(textWithUnicode);
        StringBuffer restoredText = new StringBuffer();
        while (matcher.find()) {
            String unicode = matcher.group();
            String emoji = fromUnicode(unicode);
            matcher.appendReplacement(restoredText, emoji);
        }
        matcher.appendTail(restoredText);
        return restoredText.toString();
    }

    private static String toUnicode(String input) {
        StringBuilder unicode = new StringBuilder();
        for (char character : input.toCharArray()) {
            // character 是2byte=>      0000 0000 0000 0000
            // 0x10000          => 0001 0000 0000 0000 0000
            unicode.append("\\\\u").append(Integer.toHexString(character | 0x10000).substring(1));
        }
        return unicode.toString();
    }

    private static String fromUnicode(String unicode) {
        StringBuilder emoji = new StringBuilder();
        String[] hexValues = unicode.split("\\\\u");
        for (String hex : hexValues) {
            if (!hex.isEmpty()) {
                int intValue = Integer.parseInt(hex, 16);
                emoji.append((char) intValue);
            }
        }
        return emoji.toString();
    }

    public static void main(String[] args) {
        // Byte => Character => Integer ...
        System.out.println(Character.SIZE);
        System.out.println(Integer.SIZE);

        String aa = "Hello \\ud83d\\ude00\\ud83c\\udf1f\\u002c 好 are you\\u003f \\ud83d\\ude80\\uD83C\\uDF1E";
        String s = toWithEmoji(aa);


    }
}

