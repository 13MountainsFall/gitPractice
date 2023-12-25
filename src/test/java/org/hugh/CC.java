package org.hugh;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Quarter;

import java.time.LocalDate;

/**
 * @author adward
 * @date 2023/12/4 19:01
 */
public class CC {
    public static void main(String[] args) {
        String dayStr = "2023"; // 年份字符串
        String dateNum = "2"; // 季度字符串

        int year = Integer.parseInt(dayStr);
        Quarter quarter = Quarter.of(Integer.parseInt(dateNum));

        // 计算季度的起始日期
        String quarterStart = year + "-" + ((quarter.getValue() - 1) * 3 + 1) + "-01";
        java.util.Date startOfQuarter = DateUtil.parseDate(quarterStart);

        // 计算季度的结束日期
        java.util.Date endOfQuarter = DateUtil.endOfQuarter(startOfQuarter);
        System.out.println("季度的最后一天是: " + endOfQuarter);
    }
}
