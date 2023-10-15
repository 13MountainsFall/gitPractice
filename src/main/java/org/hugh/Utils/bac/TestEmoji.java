package org.hugh.Utils.bac;

/**
 * @author adward
 * @date 2023/10/11 14:56
 */
public class TestEmoji {

    public static void main(String[] args) {
        String emoji = "😀";
        String unicode = toUnicode(emoji);
        System.out.println("unicode:" +  unicode);
        // String unicode = "\\uD83D\\uDE00"; // 这是笑脸的 Unicode 码
        String emoji2 = fromUnicode(unicode);
        System.out.println("Emoji: " + emoji);
    }



    public static String toUnicode(String input) {
        StringBuilder unicode = new StringBuilder();
        for (char character : input.toCharArray()) {
            unicode.append("\\u").append(Integer.toHexString(character | 0x10000).substring(1));
        }
        return unicode.toString();
    }

    public static String fromUnicode(String unicode) {
        StringBuilder emoji = new StringBuilder();
        String[] hexValues = unicode.split("\\\\u");
        for (String hex : hexValues) {
            if (!hex.isEmpty()) { // 避免空字符串引起的 NumberFormatException
                int intValue = Integer.parseInt(hex, 16);
                emoji.append((char) intValue);
            }
        }
        return emoji.toString();
    }


}
