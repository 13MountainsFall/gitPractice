package org.hugh.Utils.myEmojiJava2;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 首先判断目标字符串中是否包含配置的特殊字符
 * 如果有，按照配置文件代码点映射关系，依次替换
 *
 * @author gyx
 * @date 2023/10/20 08:55
 */
public class EmojiTransUtil {
    private static final String PATH = "/emojisMap.json";
    private static final Map<String, String> EMO_MAP;
    private static final Logger logger = LoggerFactory.getLogger(EmojiTransUtil.class);

    /**
     * 装载配置文件
     */

    static {
        try {
            InputStream stream = EmojiTransUtil.class.getResourceAsStream(PATH);
            if (stream != null) {
                EMO_MAP = loadEmojMaps(stream);
            } else {
                EMO_MAP = null;
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize EmojiTransProPro");
        }
    }

    /**
     * 是否包含待转换的不正常字符
     * 如果不包含，那么不必转换
     *
     * @param srcTxt
     * @return
     */
    private static boolean ifContainsUnnormal(String srcTxt) {
        try {
            if (EMO_MAP == null || EMO_MAP.isEmpty() || !StringUtils.hasLength(srcTxt)) {
                return false;
            }
            return srcTxt
                    .codePoints()
                    .mapToObj(codePoint -> {
                        String emo = String.valueOf(Character.toChars(codePoint)).toLowerCase();
                        return EMO_MAP.containsKey(emo);
                    })
                    .anyMatch(hasUnnormal -> hasUnnormal);
        } catch (IllegalArgumentException e) {
            logger.info("invalid argv for ifContainsUnnormal(String srcTxt)");
            return false;
        }

    }

    private static String transToNormal(String srcTxt) {
        try {
            if (EMO_MAP == null || EMO_MAP.isEmpty() || !ifContainsUnnormal(srcTxt)) {
                return srcTxt;
            }
            List<String> emojiList = srcTxt
                    .codePoints()
                    .mapToObj(codePoint -> {
                        String emo = String.valueOf(Character.toChars(codePoint)).toLowerCase();
                        if (EMO_MAP.containsKey(emo)) {
                            return EMO_MAP.get(emo);
                        }
                        return emo;
                    })
                    .collect(Collectors.toList());
            String joinedEmojiNormal = String.join("", emojiList);

            logger.info("===非正常字符转换前===" + srcTxt);
            logger.info("===非正常字符转换后===" + joinedEmojiNormal);

            return joinedEmojiNormal;
        } catch (Exception e) {
            logger.info("===execute failed=== transToNormal(String srcTxt)");
        }
        return srcTxt;
    }

    /**
     * 载入配置文件
     *
     * @param stream
     * @return
     */
    private static Map<String, String> loadEmojMaps(InputStream stream) {
        Map<String, String> resMap = new LinkedHashMap<>(0);
        try {
            String jsonContent = inputStreamToString(stream);
            JSONArray emojisJSON = JSONArray.parseArray(jsonContent);
            int len = emojisJSON.size();
            for (int i = 0; i < len; ++i) {
                JSONObject jobj = emojisJSON.getJSONObject(i);
                if (jobj.containsKey("srcEmoji")
                        && StringUtils.hasLength(jobj.getString("srcEmoji"))
                        && jobj.containsKey("destEmoji")
                        && StringUtils.hasLength(jobj.getString("destEmoji"))) {
                    resMap.put(jobj.getString("srcEmoji").toLowerCase(), jobj.getString("destEmoji").toLowerCase());
                }
            }
        } catch (IOException | JSONException e) {
            logger.error("===execute failed=== loadEmojMaps(InputStream stream)");
        }
        return resMap;
    }

    /**
     * 为构建 json 结构
     *
     * @param stream
     * @return
     * @throws IOException
     */
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


    public static String toWithUnicode(String input){
        input = transToNormal(input);
        StringBuilder unicodeString = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c >= '\u4e00' && c <= '\u9fa5') {
                unicodeString.append(c);
            } else if (c > 127) {
                // 中文字符或非ASCII字符，需要转换为Unicode编码
                String unicodeHex = Integer.toHexString(c | 0x10000).substring(1);
                unicodeString.append("\\u").append(unicodeHex);
            } else {
                // ASCII字符，直接添加
                unicodeString.append(c);
            }
        }
        return unicodeString.toString();
    }

//    public static void main(String[] args) {
//
//        String input = "\uE11B,Hello, 😃 ，你好World!";; // 包含表情符号和中文的字符串
//        System.out.println(ifContainsEmoji(input));
//        System.out.println(transToUnicode(input));
//        System.out.println(decodeUnicode(transToUnicode(input)));
//        System.out.println("AA".toLowerCase());
//    }

    public static void main(String[] argv){
        String a = "\\'+（%&＂         \uD83C\uDF1E\uE11B\uD83D\uDC79\uD83E\uDD2F\uD83E\uDD16\uD83C\uDDE8\uD83C\uDDF2";
        String b = "\\'+（%&＂         \ud83c\udf1e\ue11b\ud83d\udc79\ud83e\udd2f\ud83e\udd16";
        String c = "\'+\uff08%&\uff02,\ud83c\udf1e,\ud83d\udc7b,\ud83d\udc79,\ud83e\udd2f,\ud83e\udd16,\uf8ff";
//        System.out.println(toWithUnicode(c));
//        System.out.println(ifContainsEmoji(c));
//        System.out.println(decodeUnicode(toWithUnicode(c))); //"\ud83d\udc7b"
//  System.out.println(toWithUnicode(b));
//  System.out.println(decodeUnicode(toWithUnicode(b)));


        System.out.println(ifContainsEmoji("\u007F"));

        System.out.println(decodeUnicode("\u007F \\u03b5=(\\u00b4o\\uff40)"));
        System.out.println(decodeUnicode("\uD83C\uDF61"));
        System.out.println(toWithUnicode("ε=(´o｀)"));
        System.out.println(toWithUnicode("\u007F"));
        System.out.println(toWithUnicode("\uD83C\uDF61"));

    }



    public static String decodeUnicode(String unicodeString) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < unicodeString.length()) {
            char c = unicodeString.charAt(i);
            if (c == '\\' && i + 1 < unicodeString.length() && unicodeString.charAt(i + 1) == 'u') {
                // 遇到 Unicode 编码
                String hex = unicodeString.substring(i + 2, i + 6);
                char decodedChar = (char) Integer.parseInt(hex, 16);
                sb.append(decodedChar);
                i += 6;
            } else {
                // 普通字符，直接添加
                sb.append(c);
                i++;
            }
        }
        return sb.toString();
    }

    public static boolean ifContainsEmoji(String text) {
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 127 && !(c >= '\u4e00' && c <= '\u9fa5')) {
                // 包含非 ASCII、且也非中文的 字符，
                return true;
            }
        }
        return false;
    }
}

