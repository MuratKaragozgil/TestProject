package com.shopping.cart.helper;

import com.shopping.cart.model.CartItem;
import com.shopping.cart.model.DiscountType;
import com.shopping.cart.model.dto.CampaignDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Murat Karagözgil
 */
@RunWith(MockitoJUnitRunner.class)
public class CartItemHelperTest {

    @InjectMocks
    private CartItemHelper cartItemHelper;

    private CartItem cartItem;
    private CampaignDTO campaignDTO;

    private static final BigDecimal UNIT_PRICE = new BigDecimal(50);
    private static final BigDecimal DISCOUNT_AMOUNT = new BigDecimal(30);
    private static final Integer QUANTITY = 5;
    private static final Integer ITEM_LIMIT = 3;

    @Before
    public void setup() {
        cartItem = CartItem.builder() //
                .id(10L) //
                .quantity(4) //
                .unitPrice(UNIT_PRICE) //
                .totalPrice(UNIT_PRICE.multiply(new BigDecimal(QUANTITY))) //
                .campaignDiscount(BigDecimal.ZERO) //
                .finalPrice(UNIT_PRICE.multiply(new BigDecimal(QUANTITY))) //
                .build();

        campaignDTO = CampaignDTO.builder() //
                .id(10L).title("campaign") //
                .itemLimit(ITEM_LIMIT) //
                .discountAmount(DISCOUNT_AMOUNT) //
                .discountType(DiscountType.RATE) //
                .build();
    }

    @Test
    public void shouldCalculateCampaignDiscountForRate() {
        cartItemHelper.calculateCampaignDiscount(cartItem, campaignDTO);

        assertThat(cartItem.getUnitPrice(), equalTo(UNIT_PRICE));
        assertThat(cartItem.getTotalPrice(), equalTo(UNIT_PRICE.multiply(new BigDecimal(QUANTITY))));
        assertThat(cartItem.getCampaignDiscount(), equalTo(DISCOUNT_AMOUNT.multiply(new BigDecimal(2))));
        assertThat(cartItem.getFinalPrice(), equalTo(UNIT_PRICE.multiply(new BigDecimal(QUANTITY)).subtract(DISCOUNT_AMOUNT.multiply(new BigDecimal(2)))));
    }

    @Test
    public void shouldCalculateCampaignDiscountForAmount() {
        campaignDTO.setDiscountType(DiscountType.AMOUNT);
        cartItemHelper.calculateCampaignDiscount(cartItem, campaignDTO);

        assertThat(cartItem.getUnitPrice(), equalTo(UNIT_PRICE));
        assertThat(cartItem.getTotalPrice(), equalTo(UNIT_PRICE.multiply(new BigDecimal(QUANTITY))));
        assertThat(cartItem.getCampaignDiscount(), equalTo(new BigDecimal(120)));
        assertThat(cartItem.getFinalPrice(), equalTo(new BigDecimal(130)));
    }

    @Test
    public void shouldCalculateFinalPrice() {
        cartItem.setCampaignDiscount(DISCOUNT_AMOUNT);
        cartItemHelper.calculateFinalPrice(cartItem);

        assertThat(cartItem.getFinalPrice(), equalTo(new BigDecimal(220)));
    }
}