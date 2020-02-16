package com.shopping.cart.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Murat Karagözgil
 */
@RunWith(MockitoJUnitRunner.class)
public class MathUtilTest {

    @Test
    public void shouldPercentage() {
        BigDecimal result = MathUtil.percentage(new BigDecimal(100), new BigDecimal(10));
        assertThat(result, equalTo(new BigDecimal(10)));
    }

    @Test
    public void shouldIsBig() {
        boolean result = MathUtil.isBig(new BigDecimal(100), new BigDecimal(10));
        assertThat(result, equalTo(true));
    }

    @Test
    public void shouldIsBigFirstNumberNull() {
        boolean result = MathUtil.isBig(null, new BigDecimal(10));
        assertThat(result, equalTo(false));
    }

    @Test
    public void shouldIsBigSecondNumberNull() {
        boolean result = MathUtil.isBig(new BigDecimal(100), null);
        assertThat(result, equalTo(true));
    }

    @Test
    public void shouldIsBigThanZero() {
        boolean result = MathUtil.isBigThanZero(new BigDecimal(100));
        assertThat(result, equalTo(true));
    }

}