package com.shopping.cart.helper;

import com.shopping.cart.model.CartItem;
import com.shopping.cart.model.DiscountType;
import com.shopping.cart.model.ShoppingCart;
import com.shopping.cart.model.dto.CouponDTO;
import com.shopping.cart.utils.MathUtil;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author Murat Karagözgil
 */

@Component
public class ShoppingCartHelper {

    public void calculateDisplayPrice(ShoppingCart shoppingCart) {
        BigDecimal totalFinalPrice = shoppingCart.getCartItems() //
                .stream() //
                .map(CartItem::getFinalPrice) //
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalCampaignDiscount = shoppingCart.getCartItems() //
                .stream() //
                .map(CartItem::getCampaignDiscount) //
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal displayPrice = totalFinalPrice;
        if (shoppingCart.getCouponDiscount() != null) {
            displayPrice = displayPrice.subtract(shoppingCart.getCouponDiscount());
        }
        if (shoppingCart.getDeliveryCost() != null) {
            displayPrice = displayPrice.add(shoppingCart.getDeliveryCost());
        }

        shoppingCart.setCampaignDiscount(totalCampaignDiscount);
        shoppingCart.setTotalPrice(totalFinalPrice);
        shoppingCart.setDisplayPrice(displayPrice);
    }

    public void calculateCouponDiscount(ShoppingCart shoppingCart, CouponDTO coupon) {
        DiscountType discountType = coupon.getDiscountType();
        BigDecimal couponDiscount = BigDecimal.ZERO;

        switch (discountType) {
            case RATE:
                couponDiscount = MathUtil.percentage(shoppingCart.getTotalPrice(), coupon.getDiscountAmount());
                break;
            case AMOUNT:
                couponDiscount = coupon.getDiscountAmount();
                break;
        }

        shoppingCart.setCouponDiscount(couponDiscount);
    }
}
