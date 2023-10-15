//package org.hugh.D01_file;
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
// * @author adward
// * @date 2023/10/11 15:11
// */
//public class TestEmoji2 {
//    public static void main(String[] args) {
//        String textWithEmojis = "Hello 😀🌟, how are you? 🚀";
//        String emojiRegex = "\\p{So}"; // 正则表达式用于匹配表情符号
//
//        // 替换表情符号为它们的 Unicode 表示
//        String modifiedText = textWithEmojis.replaceAll(emojiRegex, emoji -> toUnicode(emoji));
//
//        System.out.println("Original Text: " + textWithEmojis);
//        System.out.println("Modified Text: " + modifiedText);
//    }
//
//    public static String toUnicode(String input) {
//        StringBuilder unicode = new StringBuilder();
//        for (char character : input.toCharArray()) {
//            unicode.append("\\u").append(Integer.toHexString(character | 0x10000).substring(1));
//        }
//        return unicode.toString();
//    }
//}
