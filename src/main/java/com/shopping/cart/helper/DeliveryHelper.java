package com.shopping.cart.helper;

import com.shopping.cart.model.ShoppingCart;
import com.shopping.cart.model.dto.DeliveryCostDTO;
import com.shopping.cart.repository.CartItemRepository;
import com.shopping.cart.utils.DeliveryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author Murat Karagözgil
 */
@Component
public class DeliveryHelper {

    @Autowired
    private CartItemRepository cartItemRepository;

    public void calculateDelivery(ShoppingCart shoppingCart, DeliveryCostDTO deliveryCostDTO) {
        int numberOfDeliveries = getNumberOfDeliveries(shoppingCart);
        int numberOfProducts = getNumberOfProducts(shoppingCart);

        DeliveryUtil deliveryUtil = new DeliveryUtil(deliveryCostDTO.getCostPerDelivery(), deliveryCostDTO.getCostPerProduct());
        BigDecimal deliveryCost = deliveryUtil.calculateFor(numberOfDeliveries, numberOfProducts);

        shoppingCart.setDeliveryCost(deliveryCost);
    }

    private int getNumberOfDeliveries(ShoppingCart shoppingCart) {
        return cartItemRepository.findNumberOfDistinctCategory(shoppingCart);
    }

    private int getNumberOfProducts(ShoppingCart shoppingCart) {
        return shoppingCart.getCartItems().size();
    }

}
