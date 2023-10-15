package org.hugh.Utils;

/**
 * @author adward
 * @date 2023/10/11 16:58
 */
public class TestIt02 {
    public static void main(String[] args) {
        String textWithEmojis = "Hello 😀🌟, 好 are you? 🚀";
        String text = "Hello  好 are you ";
        System.out.println("===》" + EmojiTransUtil.ifContainsEmoji(textWithEmojis)); //
        String s = EmojiTransUtil.fromWithEmoji(textWithEmojis);
        System.out.println(s);
        System.out.println("============");
        System.out.println("===》" + EmojiTransUtil.ifContainsUnicode(s));
        String s1 = EmojiTransUtil.toWithEmoji(s + "\\uD83C\\uDF1E");
        System.out.println(s1);


    }

}
