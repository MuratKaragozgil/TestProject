package com.shopping.cart.helper;

import com.shopping.cart.model.CartItem;
import com.shopping.cart.model.ShoppingCart;
import com.shopping.cart.model.dto.DeliveryCostDTO;
import com.shopping.cart.repository.CartItemRepository;
import com.shopping.cart.utils.MathUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Murat Karagözgil
 */
@RunWith(MockitoJUnitRunner.class)
public class DeliveryHelperTest {

    @InjectMocks
    private DeliveryHelper deliveryHelper;

    @Mock
    private CartItemRepository cartItemRepository;

    @Test
    public void shouldCalculateDelivery() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(1L);

        CartItem cartItem = CartItem.builder()
                .id(2L) //
                .quantity(1) //
                .campaignDiscount(new BigDecimal(500)) //
                .build();
        shoppingCart.setCartItems(new HashSet<>(Collections.singletonList(cartItem)));

        Mockito.when(cartItemRepository.findNumberOfDistinctCategory(shoppingCart)).thenReturn(2);

        DeliveryCostDTO deliveryCostDTO = new DeliveryCostDTO(new BigDecimal(2), new BigDecimal(2));
        deliveryHelper.calculateDelivery(shoppingCart, deliveryCostDTO);

        assertThat(MathUtil.isBigThanZero(shoppingCart.getDeliveryCost()), equalTo(true));
    }
}