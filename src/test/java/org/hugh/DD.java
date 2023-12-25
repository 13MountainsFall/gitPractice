package org.hugh;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author adward
 * @date 2023/12/5 19:29
 */
public class DD {
    public static void main(String[] args) {
        // System.out.println(getTouchRate(null, 1000L));
        System.out.println(Integer.parseInt("2023") + 1 + "-01-01");

    }
    public static BigDecimal getTouchRate(Long followedCnt, Long totalCnt) {
        if (followedCnt == null || followedCnt == 0 || totalCnt == null || totalCnt == 0){
            return BigDecimal.ZERO;
        }
        return new BigDecimal(followedCnt).divide(new BigDecimal(totalCnt), 6 , RoundingMode.HALF_UP);
    }
}
