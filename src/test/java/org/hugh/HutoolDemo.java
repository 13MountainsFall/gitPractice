package org.hugh;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author adward
 * @date 2023/12/18 16:12
 */
public class HutoolDemo {
    public static void main(String[] args) {
//        ArrayList<String> arrayList = new ArrayList<>();
//        System.out.println(ObjectUtil.isEmpty(arrayList));
//        String a = "1676880414934";
//        System.out.println(new Date(Long.parseLong(a)));
        // 示例日期
        Date date1 = DateUtil.parse("2023-01-01 23:00:00");
        Date date2 = DateUtil.parse("2023-01-10");

        // 计算两个日期之间的间隔
        long days = DateUtil.between(date1, date2, DateUnit.DAY);
        long hours = DateUtil.between(date1, date2, DateUnit.HOUR);
        long minutes = DateUtil.between(date1, date2, DateUnit.MINUTE);


        // 打印结果
        System.out.println("Days: " + days);
        System.out.println("Hours: " + hours);
        System.out.println("Minutes: " + minutes);
    }
}
