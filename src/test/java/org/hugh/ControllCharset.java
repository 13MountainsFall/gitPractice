package org.hugh;

/**
 * @author adward
 * @date 2023/12/27 09:20
 */
public class ControllCharset {
    public static void main(String[] args) {
        String nickname = "用户昵称\u007F";
        nickname = nickname.replaceAll("\\p{Cntrl}", "");
        System.out.println(nickname);
    }
}
