package com.shopping.cart.utils;

import java.math.BigDecimal;

/**
 * @author Murat Karagözgil
 */
public class MathUtil {

    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    public static BigDecimal percentage(BigDecimal base, BigDecimal pct) {
        return base.multiply(pct).divide(ONE_HUNDRED);
    }

    public static boolean isBig(BigDecimal first, BigDecimal second) {
        return first != null && second == null || first != null && first.compareTo(second) > 0;
    }

    public static boolean isBigThanZero(BigDecimal number) {
        return isBig(number, BigDecimal.ZERO);
    }
}