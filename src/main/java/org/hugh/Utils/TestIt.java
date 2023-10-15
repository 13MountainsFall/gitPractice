package org.hugh.Utils;

/**
 * @author adward
 * @date 2023/10/11 16:58
 */
public class TestIt {
    public static void main(String[] args) {
        String textWithEmojis = "Hello 😀🌟, 好 are you? 🚀";
        String text = "Hello  好 are you ";
        System.out.println("===》" + EmojiTransUtil_Bac.ifWithEmoji(text)); //
        String s = EmojiTransUtil_Bac.fromWithEmoji(textWithEmojis);
        System.out.println(s);
        System.out.println("============");
        System.out.println("===》" + EmojiTransUtil_Bac.ifWithUnicode(text));
        String s1 = EmojiTransUtil_Bac.toWithEmoji(s + "\\uD83C\\uDF1E");
        System.out.println(s1);


    }

}
