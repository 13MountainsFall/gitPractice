package org.hugh.Utils.unicodeTrans;

import org.hugh.Utils.myEmojiJava2.EmojiTransUtil;
import org.hugh.Utils.myEmojiJava2.EmojiTransUtilPro;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author adward
 * @date 2023/10/24 09:44
 */
public class UnicodeTrans {
    public static void main(String[] args) {
//        String s = "\\uc563\\ue561\\ud37e\\u4833\\u9f62\\u2c8b\\u328d\\ufdd1";
//        System.out.println(EmojiTransUtil.decodeUnicode(s));
        Map m = new HashMap<>();
        m.put("a", null);
        System.out.println(m.getOrDefault("a", ""));
    }


}
