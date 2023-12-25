package org.hugh;

/**
 * @author adward
 * @date 2023/11/3 13:56
 */
public class TestSwitch {
    public static void main(String[] args) {
        String a = null;
        switch (a)
        {
            case "a":
                System.out.println("ok");
                break;
            case "b":
                System.out.println("no");
                break;
            default:
                System.out.println("hh");
                break;
        }
    }

}
