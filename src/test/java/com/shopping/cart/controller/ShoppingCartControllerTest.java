package com.shopping.cart.controller;

import com.shopping.cart.model.dto.ShoppingCartDTO;
import com.shopping.cart.request.CampaignRequest;
import com.shopping.cart.request.CouponRequest;
import com.shopping.cart.request.ItemRequest;
import com.shopping.cart.service.ShoppingCartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;


/**
 * @author Murat Karagözgil
 */
@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartControllerTest {

    @InjectMocks
    private ShoppingCartController shoppingCartController;

    @Mock
    private ShoppingCartService cartService;

    @Mock
    private MockHttpServletRequest request;

    private static final ShoppingCartDTO SHOPPING_CART_DTO = ShoppingCartDTO.builder()
            .displayPrice(new BigDecimal(80))
            .totalPrice(new BigDecimal(100))
            .couponDiscount(BigDecimal.ZERO)
            .campaignDiscount(new BigDecimal(20))
            .id(1L) //
            .build();

    @Test
    public void shouldSaveShoppingCartCorrectly() {
        when(cartService.saveShoppingCart()).thenReturn(SHOPPING_CART_DTO);
        ResponseEntity<ShoppingCartDTO> shoppingCartDTOResponseEntity = shoppingCartController.saveShoppingCart(request);
        assertThat(shoppingCartDTOResponseEntity.getBody(), equalTo(SHOPPING_CART_DTO));
    }

    @Test
    public void shouldPrintAllFieldsOfTheCart() {
        when(cartService.getShoppingCartById(Mockito.anyLong())).thenReturn(SHOPPING_CART_DTO);
        ResponseEntity<ShoppingCartDTO> shoppingCartDTOResponseEntity = shoppingCartController.getShoppingCartById(request, SHOPPING_CART_DTO.getId());
        assertThat(shoppingCartDTOResponseEntity.getBody(), equalTo(SHOPPING_CART_DTO));
    }

    @Test
    public void shouldAddItemSuccessfully() {
        when(cartService.addItem(Mockito.anyLong(), Mockito.anyLong())).thenReturn(SHOPPING_CART_DTO);
        ResponseEntity<ShoppingCartDTO> shoppingCartDTOResponseEntity = shoppingCartController.addItem(request, ItemRequest.builder().productId(1L).shoppingCartId(1L).build());
        assertThat(shoppingCartDTOResponseEntity.getBody(), equalTo(SHOPPING_CART_DTO));
    }

    @Test
    public void shouldRemoveItemSuccessfully() {
        when(cartService.removeItem(Mockito.anyLong(), Mockito.anyLong())).thenReturn(SHOPPING_CART_DTO);
        ResponseEntity<ShoppingCartDTO> shoppingCartDTOResponseEntity = shoppingCartController.removeItem(request, ItemRequest.builder().productId(1L).shoppingCartId(1L).build());
        assertThat(shoppingCartDTOResponseEntity.getBody(), equalTo(SHOPPING_CART_DTO));
    }

    @Test
    public void shouldApplyCampaignSuccessfully() {
        when(cartService.applyCampaign(Mockito.anyLong(), Mockito.anyLong())).thenReturn(SHOPPING_CART_DTO);
        ResponseEntity<ShoppingCartDTO> shoppingCartDTOResponseEntity = shoppingCartController.applyCampaign(request, CampaignRequest.builder().campaignId(1L).shoppingCartId(1L).build());
        assertThat(shoppingCartDTOResponseEntity.getBody(), equalTo(SHOPPING_CART_DTO));
    }

    @Test
    public void shouldApplyCouponSuccessfully() {
        when(cartService.applyCoupon(Mockito.anyLong(), Mockito.anyLong())).thenReturn(SHOPPING_CART_DTO);
        ResponseEntity<ShoppingCartDTO> shoppingCartDTOResponseEntity = shoppingCartController.applyCoupon(request, CouponRequest.builder().couponId(1L).shoppingCartId(1L).build());
        assertThat(shoppingCartDTOResponseEntity.getBody(), equalTo(SHOPPING_CART_DTO));
    }
}