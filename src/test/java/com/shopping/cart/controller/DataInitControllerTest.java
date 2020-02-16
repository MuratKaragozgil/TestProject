package com.shopping.cart.controller;

import com.shopping.cart.model.dto.ShoppingCartDTO;
import com.shopping.cart.repository.*;
import com.shopping.cart.service.ShoppingCartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

/**
 * @author Murat Karagözgil
 */
@RunWith(MockitoJUnitRunner.class)
public class DataInitControllerTest {

    @InjectMocks
    private DataInitController dataInitController;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CampaignRepository campaignRepository;

    @Mock
    private CouponRepository couponRepository;

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @Mock
    private ShoppingCartService shoppingCartService;

    @Test
    public void shouldInitializeDataSuccessfully() {
        dataInitController.initOnlyParamData();
    }

    @Test
    public void shouldInitializeFullDataSuccessfully() {
        when(shoppingCartService.saveShoppingCart()).thenReturn(ShoppingCartDTO.builder().id(1L).build());
        dataInitController.initFullData();
    }

    @Test
    public void addProductsToCartSuccessfully() {
        when(shoppingCartService.saveShoppingCart()).thenReturn(ShoppingCartDTO.builder().id(1L).build());
        dataInitController.addProductsToCart();
    }

}