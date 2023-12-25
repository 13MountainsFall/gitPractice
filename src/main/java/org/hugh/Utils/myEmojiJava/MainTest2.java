package org.hugh.Utils.myEmojiJava;
import com.vdurmont.emoji.EmojiParser;
/**
 * @author adward
 * @date 2023/10/19 21:05
 */
public class MainTest2 {
    public static void main(String[] args) {
        // 将字符串中的 Emoji 转换为别名
        String text = "I love Java! 😍";
        String aliasText = EmojiParser.parseToAliases(text);
        System.out.println(aliasText); // 输出: "I love Java! :heart_eyes:"

        // 将字符串中的别名转换为 Emoji
        String aliasText2 = "I love :heart_eyes: Java! :heart_eyes:";
        String emojiText = EmojiParser.parseToUnicode(aliasText2);
        System.out.println(emojiText); // 输出: "I love Java! 😍"

        // 检查字符串是否包含 Emoji
        boolean hasEmoji = EmojiManager.containsEmoji(text);
        System.out.println("Contains Emoji: " + hasEmoji);

    }
}
