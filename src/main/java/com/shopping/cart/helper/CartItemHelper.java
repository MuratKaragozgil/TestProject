package com.shopping.cart.helper;

import com.shopping.cart.model.CartItem;
import com.shopping.cart.model.DiscountType;
import com.shopping.cart.model.dto.CampaignDTO;
import com.shopping.cart.utils.MathUtil;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author Murat Karagözgil
 */
@Component
public class CartItemHelper {

    public void calculateCampaignDiscount(CartItem cartItem, CampaignDTO campaign) {
        DiscountType discountType = campaign.getDiscountType();
        BigDecimal discountPerProduct = BigDecimal.ZERO;

        switch (discountType) {
            case RATE:
                discountPerProduct = MathUtil.percentage(cartItem.getUnitPrice(), campaign.getDiscountAmount());
                break;
            case AMOUNT:
                discountPerProduct = campaign.getDiscountAmount();
                break;
        }

        BigDecimal campaignDiscount = discountPerProduct.multiply(new BigDecimal(cartItem.getQuantity()));
        cartItem.setCampaignDiscount(campaignDiscount);

        calculateFinalPrice(cartItem);
    }

    public void calculateFinalPrice(CartItem cartItem) {
        BigDecimal newFinalPrice = BigDecimal.ZERO;

        if (cartItem.getTotalPrice() != null) {
            newFinalPrice = newFinalPrice.add(cartItem.getTotalPrice());
        }
        if (cartItem.getCampaignDiscount() != null) {
            newFinalPrice = newFinalPrice.subtract(cartItem.getCampaignDiscount());
        }

        cartItem.setFinalPrice(newFinalPrice);
    }
}
