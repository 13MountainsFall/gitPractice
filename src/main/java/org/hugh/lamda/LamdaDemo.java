package org.hugh.lamda;

/**
 * @author adward
 * @date 2023/10/22 20:20
 */
public class LamdaDemo {
    public static void main(String[] args) {
        // 函数式接口
        InterfaceA pp = () -> System.out.println("aaa");
        pp.run();

        InterfaceB addF = Integer::sum;
        InterfaceB subF = (a, b) -> a - b;
        System.out.println(addF.cal(1,2));
        System.out.println(subF.cal(1,2));
    }
}
