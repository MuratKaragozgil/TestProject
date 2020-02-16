package com.shopping.cart.utils;

import java.math.BigDecimal;

/**
 * @author Murat Karagözgil
 */
public class DeliveryUtil {

    private final BigDecimal FIXED_COST = new BigDecimal("2.99");

    private BigDecimal costPerDelivery;
    private BigDecimal costPerProduct;

    public DeliveryUtil(BigDecimal costPerDelivery, BigDecimal costPerProduct) {
        this.costPerDelivery = costPerDelivery;
        this.costPerProduct = costPerProduct;
    }

    public BigDecimal calculateFor(int numberOfDeliveries, int numberOfProducts) {
        BigDecimal delivery = costPerDelivery.multiply(new BigDecimal(numberOfDeliveries));
        BigDecimal product = costPerProduct.multiply(new BigDecimal(numberOfProducts));

        return delivery.add(product).add(FIXED_COST);
    }
}

