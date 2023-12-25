package org.hugh;

import org.springframework.beans.BeanUtils;

/**
 * @author adward
 * @date 2023/11/16 15:51
 */
public class CopyTest {
    public static void main(String[] args) {
        Student zhangsan = new Student("zhangsan", "1");
        Student zhangsi = new Student();
        BeanUtils.copyProperties(zhangsan, zhangsi);
        System.out.println(zhangsi);
    }
}
