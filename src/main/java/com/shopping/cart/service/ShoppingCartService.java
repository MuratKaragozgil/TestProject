package com.shopping.cart.service;

import com.shopping.cart.model.ShoppingCart;
import com.shopping.cart.model.dto.DeliveryCostDTO;
import com.shopping.cart.model.dto.ShoppingCartDTO;

/**
 * @author Murat Karagözgil
 */
public interface ShoppingCartService {

    ShoppingCartDTO saveShoppingCart();

    ShoppingCartDTO getShoppingCartById(Long id);

    ShoppingCartDTO addItem(Long shoppingCartId, Long productId);

    ShoppingCartDTO removeItem(Long shoppingCartId, Long productId);

    ShoppingCartDTO applyCoupon(Long shoppingCartId, Long couponId);

    ShoppingCartDTO applyCampaign(Long shoppingCartId, Long campaignId);

    ShoppingCartDTO applyDelivery(Long shoppingCartId, DeliveryCostDTO deliveryCostDTO);

    ShoppingCart updateShoppingCartPriceData(ShoppingCart shoppingCart);
}
