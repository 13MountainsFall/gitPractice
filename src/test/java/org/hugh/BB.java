package org.hugh;

import java.time.LocalDate;
import java.util.Date;
import cn.hutool.core.date.DateUtil;

/**
 * @author adward
 * @date 2023/12/4 18:47
 */
public class BB {
    public static void main(String[] args) {
        // add 2
        String dateString = "2023-12";
        Date date = DateUtil.parse(dateString, "yyyy-MM");
        Date endOfMonth = DateUtil.endOfMonth(date);
        String endOfMonth2 = DateUtil.endOfMonth(date).toString();

        System.out.println(endOfMonth2);
        // 输出月末日期
        System.out.println("月末日期: " + DateUtil.format(endOfMonth, "yyyy-MM-dd"));


        System.out.println(LocalDate.now().toString());
    }
}
