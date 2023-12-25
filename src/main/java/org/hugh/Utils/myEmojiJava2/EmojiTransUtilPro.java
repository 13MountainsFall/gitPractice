package org.hugh.Utils.myEmojiJava2;

import org.json.JSONObject;
import org.json.JSONArray;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class EmojiTransUtilPro {
    private static final String PATH = "/emojisMap.json";
    private static final Map<String, String> SINGLE_EMO_MAP;
    /**
     * 除了中文、英文大小写、英文标点，其他默认都是emoji
     */
    private static final String EMOJI_PATTERN = "[^\\x{0000}-\\x{007F}\\x{4E00}-\\x{9FFF}]";
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
        // 标记char位置，验证前后关系
        AtomicInteger charIntex = new AtomicInteger(0);
        while (matcher.find()) {
            charIntex.incrementAndGet();
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
            // 配置文件的srcEmoji从前往后，应是长的在前，替换的时候先替换长的
            unicode.append("\\\\u").append(getRelUnicode(Integer.toHexString(character | 0x10000).substring(1)));
        }
        return unicode.toString();
    }

    private static String getRelUnicode(String s) {
        // 如果是 2 换2 采用配置两个、
        // 需要人为设计好替换
        return SINGLE_EMO_MAP.getOrDefault(s.toLowerCase(), s);
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


    static {
        InputStream stream = EmojiTransUtilPro.class.getResourceAsStream(PATH);

        SINGLE_EMO_MAP = loadEmojMaps(stream);
    }

    private static Map<String, String> loadEmojMaps(InputStream stream) {
        Map<String, String> resMap = null;
        try {
            JSONArray emojisJSON = new JSONArray(inputStreamToString(stream));
            int len = emojisJSON.length();
            resMap = new LinkedHashMap<String, String>(len);
            for (int i = 0; i < len; ++i) {
                JSONObject jobj = emojisJSON.getJSONObject(i);
                if (jobj.has("srcEmoji")
                        && StringUtils.hasLength(jobj.getString("srcEmoji"))
                        && jobj.has("destEmoji")
                        && StringUtils.hasLength(jobj.getString("destEmoji"))) {
                    resMap.put(jobj.getString("srcEmoji"), jobj.getString("destEmoji"));
                }
            }
        } catch (IOException e) {
            System.out.println("failed");
        }
        return resMap;
    }

    private static String inputStreamToString(InputStream stream) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader isr = new InputStreamReader(stream, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String read;
        while ((read = br.readLine()) != null) {
            sb.append(read);
        }
        br.close();
        return sb.toString();
    }


    public static void main(String[] args) {
        // Byte => Character => Integer ...
//        System.out.println(Character.SIZE);
//        System.out.println(Integer.SIZE);
//
//        String aa = "Hello \\ud83d\\ude00\\ud83c\\udf1f\\u002c 好 are you\\u003f \\ud83d\\ude80\\uD83C\\uDF1E";
//        String s = toWithEmoji(aa);
//        AtomicInteger counter = new AtomicInteger(0);
//        counter.incrementAndGet();
//        int a = counter.get();
//        System.out.println(a);

        String a = "你好\uD83C\uDF1E\uE11B\uD83D\uDC79\uD83E\uDD2F\uD83E\uDD16";
        String b = "你好,。》0,+_@#$%￥\uD83C\uDF1E￥%\uD83D\uDC7B\uD83D\uDC79\uD83E\uDD2F\uD83E\uDD16";
        System.out.println(b.toCharArray().length);
//        🌞,,👹,🤯,🤖
// =======================================================================
        List<String> emojiList = b
                .codePoints()
                .mapToObj(codePoint -> {
                    String emo = String.valueOf(Character.toChars(codePoint));
                    if (SINGLE_EMO_MAP.containsKey(emo) ){
                        return SINGLE_EMO_MAP.get(emo);
                    }
                    return emo;
                })
                .collect(Collectors.toList());
        System.out.println(emojiList);
        String joinedEmoji = String.join("", emojiList);
        System.out.println(joinedEmoji);


    }
}

