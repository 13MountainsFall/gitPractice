package org.hugh.Utils.bac;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author adward
 * @date 2023/10/11 16:07
 *
 * 在 Java 中，正则表达式 \p{So} 用于匹配符号字符 (Symbol) 的 Unicode 属性。符号字符中包括了一些表情符号、箭头、货币符号等。这个正则表达式是通过 Unicode 字符属性来定义的，但你可以根据需要调整它以匹配不同的字符类别。
 *
 *https://wenku.baidu.com/view/0326e39182c758f5f61fb7360b4c2e3f5627253e.html?_wkts_=1697014043805&bdQuery=emoji表情包等特殊字符的正则%5C%7BSo%7D
 * 1. 匹配所有数学符号：\p{Sm}
 * 2. 匹配所有货币符号：\p{Sc}
 * 3. 匹配所有符号图案：\p{So}
 * 4. 匹配所有表情符号：\p{So} 或 \p{Emoji}
 * 5. 匹配所有特殊字符：\p{So} 或 \p{Symbol}
 */
public class TestEmoji3 {
    public static void main(String[] args) {
        String textWithEmojis = "Hello 😀🌟, how are you? 🚀";
        // 正则表达式用于匹配表情符号
        //
        String emojiRegex = "\\p{So}";


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
    }

    private static String toUnicode(String input) {
        StringBuilder unicode = new StringBuilder();
        for (char character : input.toCharArray()) {
            unicode.append("\\\\u").append(Integer.toHexString(character | 0x10000).substring(1));
        }
        return unicode.toString();
    }
}
