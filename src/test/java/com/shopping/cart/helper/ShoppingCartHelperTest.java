package com.shopping.cart.helper;

import com.shopping.cart.model.CartItem;
import com.shopping.cart.model.DiscountType;
import com.shopping.cart.model.ShoppingCart;
import com.shopping.cart.model.dto.CouponDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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
public class ShoppingCartHelperTest {

    @InjectMocks
    private ShoppingCartHelper shoppingCartHelper;

    @Test
    public void shouldCalculateDisplayPrice() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(1L);

        CartItem cartItem = CartItem.builder()
                .id(2L) //
                .quantity(4) //
                .unitPrice(new BigDecimal(100)) //
                .totalPrice(new BigDecimal(400)) //
                .campaignDiscount(BigDecimal.ZERO) //
                .finalPrice(new BigDecimal(400)) //
                .build();
        shoppingCart.setCartItems(new HashSet<>(Collections.singletonList(cartItem)));
        shoppingCart.setCouponDiscount(new BigDecimal(50));

        shoppingCartHelper.calculateDisplayPrice(shoppingCart);
        assertThat(shoppingCart.getDisplayPrice(), equalTo(new BigDecimal(350)));
    }

    @Test
    public void shouldCalculateCouponDiscountForRate() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(1L);
        shoppingCart.setTotalPrice(new BigDecimal(400));

        CouponDTO coupon = CouponDTO.builder()
                .id(10L) //
                .title("coupon") //
                .minPurchaseAmount(new BigDecimal(100)) //
                .discountAmount(new BigDecimal(50)) //
                .discountType(DiscountType.RATE) //
                .build();

        shoppingCartHelper.calculateCouponDiscount(shoppingCart, coupon);
        assertThat(shoppingCart.getCouponDiscount(), equalTo(new BigDecimal(200)));
    }

    @Test
    public void shouldCalculateCouponDiscountForAmount() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(1L);
        shoppingCart.setTotalPrice(new BigDecimal(400));

        CouponDTO coupon = CouponDTO.builder() //
                .id(2L) //
                .title("coupon") //
                .minPurchaseAmount(new BigDecimal(100)) //
                .discountAmount(new BigDecimal(50)) //
                .discountType(DiscountType.AMOUNT) //
                .build();

        shoppingCartHelper.calculateCouponDiscount(shoppingCart, coupon);
        assertThat(shoppingCart.getCouponDiscount(), equalTo(new BigDecimal(50)));
    }
}