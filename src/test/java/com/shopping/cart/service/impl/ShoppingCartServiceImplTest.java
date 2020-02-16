package com.shopping.cart.service.impl;

import com.shopping.cart.helper.CartItemHelper;
import com.shopping.cart.helper.DeliveryHelper;
import com.shopping.cart.helper.ShoppingCartHelper;
import com.shopping.cart.mapper.ShoppingCartMapper;
import com.shopping.cart.model.*;
import com.shopping.cart.model.dto.CampaignDTO;
import com.shopping.cart.model.dto.CouponDTO;
import com.shopping.cart.model.dto.DeliveryCostDTO;
import com.shopping.cart.model.dto.ShoppingCartDTO;
import com.shopping.cart.repository.CartItemRepository;
import com.shopping.cart.repository.ProductRepository;
import com.shopping.cart.repository.ShoppingCartRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author Murat Karagözgil
 */
@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartServiceImplTest {

    @InjectMocks
    private ShoppingCartServiceImpl shoppingCartService;

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ShoppingCartMapper shoppingCartMapper;

    @Mock
    private CampaignServiceImpl campaignService;

    @Mock
    private CouponServiceImpl couponService;

    @Mock
    private CartItemHelper cartItemHelper;

    @Mock
    private ShoppingCartHelper shoppingCartHelper;

    @Mock
    private DeliveryHelper deliveryHelper;

    private Product product;

    @Before
    public void setup() {
        product = Product.builder()
                .id(1L) //
                .title("product") //
                .price(new BigDecimal(500)) //
                .build();
    }

    @Test
    public void shouldPrint() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(2L);
        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();

        Mockito.when(shoppingCartRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(shoppingCart));
        Mockito.when(shoppingCartMapper.convertToDTO(shoppingCart)).thenReturn(shoppingCartDTO);

        shoppingCartService.getShoppingCartById(shoppingCart.getId());
    }

    @Test
    public void shouldAddItemNewCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(2L);

        Mockito.when(shoppingCartRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(shoppingCart));
        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(product));
        Mockito.when(shoppingCartRepository.save(Mockito.any())).thenReturn(shoppingCart);
        Mockito.when(shoppingCartMapper.convertToDTO(Mockito.any())).thenReturn(new ShoppingCartDTO());

        shoppingCartService.addItem(shoppingCart.getId(), product.getId());

        Mockito.verify(cartItemHelper).calculateFinalPrice(Mockito.any());
        Mockito.verify(shoppingCartHelper).calculateDisplayPrice(Mockito.any());
    }

    @Test
    public void shouldAddItemExistCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(2L);

        CartItem cartItem = CartItem.builder() //
                .id(20L) //
                .shoppingCart(shoppingCart) //
                .product(product) //
                .quantity(1) //
                .unitPrice(new BigDecimal(500)) //
                .build();
        shoppingCart.setCartItems(new HashSet<>(Collections.singletonList(cartItem)));

        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(product));
        Mockito.when(shoppingCartRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(shoppingCart));
        Mockito.when(shoppingCartRepository.save(Mockito.any())).thenReturn(shoppingCart);
        Mockito.when(shoppingCartMapper.convertToDTO(Mockito.any())).thenReturn(new ShoppingCartDTO());

        shoppingCartService.addItem(shoppingCart.getId(), product.getId());

        Mockito.verify(cartItemHelper).calculateFinalPrice(Mockito.any());
        Mockito.verify(shoppingCartHelper).calculateDisplayPrice(Mockito.any());
    }

    @Test
    public void shouldThrowExceptionWhenTryingToAddNoExistProduct() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(2L);

        CartItem cartItem = CartItem.builder() //
                .id(20L) //
                .shoppingCart(shoppingCart) //
                .product(product) //
                .quantity(1) //
                .unitPrice(new BigDecimal(500)) //
                .build();
        shoppingCart.setCartItems(new HashSet<>(Collections.singletonList(cartItem)));

        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(product));
        Mockito.when(shoppingCartRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(shoppingCart));
        Mockito.when(shoppingCartRepository.save(Mockito.any())).thenReturn(shoppingCart);
        Mockito.when(shoppingCartMapper.convertToDTO(Mockito.any())).thenReturn(new ShoppingCartDTO());

        shoppingCartService.addItem(shoppingCart.getId(), product.getId());

        Mockito.verify(cartItemHelper).calculateFinalPrice(Mockito.any());
        Mockito.verify(shoppingCartHelper).calculateDisplayPrice(Mockito.any());
    }

    @Test
    public void shouldRemoveItemAfterEmptyCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(2L);

        CartItem cartItem = CartItem.builder().id(20L).product(product).shoppingCart(shoppingCart)
                .product(product).quantity(1).unitPrice(new BigDecimal(500)).build();
        shoppingCart.setCartItems(new HashSet<>(Collections.singletonList(cartItem)));

        Mockito.when(shoppingCartRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(shoppingCart));
        Mockito.when(shoppingCartRepository.save(Mockito.any())).thenReturn(shoppingCart);
        Mockito.when(shoppingCartMapper.convertToDTO(Mockito.any())).thenReturn(new ShoppingCartDTO());

        shoppingCartService.removeItem(shoppingCart.getId(), product.getId());

        Mockito.verify(cartItemRepository).delete(Mockito.any());
        Mockito.verify(shoppingCartHelper).calculateDisplayPrice(Mockito.any());
    }

    @Test
    public void shouldRemoveItemAfterHasCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(2L);

        CartItem cartItem = CartItem.builder().id(20L).product(product).shoppingCart(shoppingCart)
                .product(product).quantity(3).unitPrice(new BigDecimal(500)).build();
        shoppingCart.setCartItems(new HashSet<>(Collections.singletonList(cartItem)));

        Mockito.when(shoppingCartRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(shoppingCart));
        Mockito.when(shoppingCartRepository.save(Mockito.any())).thenReturn(shoppingCart);
        Mockito.when(shoppingCartMapper.convertToDTO(Mockito.any())).thenReturn(new ShoppingCartDTO());

        shoppingCartService.removeItem(shoppingCart.getId(), product.getId());

        Mockito.verify(cartItemHelper).calculateFinalPrice(Mockito.any());
        Mockito.verify(shoppingCartHelper).calculateDisplayPrice(Mockito.any());
    }

    @Test
    public void shouldApplyCampaign() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(2L);

        Category category = Category.builder()
                .id(10L) //
                .title("category") //
                .build();
        Campaign campaign = Campaign.builder()
                .id(10L) //
                .title("campaign") //
                .category(category) //
                .itemLimit(1) //
                .build();
        CampaignDTO campaignDTO = CampaignDTO.builder()
                .id(10L) //
                .title("campaign") //
                .categoryId(category.getId()) //
                .itemLimit(1) //
                .build();
        CartItem cartItem = CartItem.builder()
                .id(20L) //
                .product(product) //
                .quantity(4) //
                .build();

        Set<CartItem> cartItems = new HashSet<>(Collections.singletonList(cartItem));
        shoppingCart.setCartItems(cartItems);

        Mockito.when(campaignService.getCampaignById(Mockito.anyLong())).thenReturn(campaignDTO);
        Mockito.when(cartItemRepository.findByCategoryId(Mockito.anyLong())).thenReturn(cartItems);
        Mockito.when(shoppingCartRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(shoppingCart));
        Mockito.when(shoppingCartRepository.save(Mockito.any())).thenReturn(shoppingCart);
        Mockito.when(shoppingCartMapper.convertToDTO(Mockito.any())).thenReturn(new ShoppingCartDTO());

        shoppingCartService.applyCampaign(shoppingCart.getId(), campaign.getId());

        Mockito.verify(cartItemHelper, Mockito.times(cartItems.size())).calculateCampaignDiscount(Mockito.any(), Mockito.any());
        Mockito.verify(shoppingCartHelper).calculateDisplayPrice(Mockito.any());
    }

    @Test
    public void shouldApplyCoupon() {
        Coupon coupon = Coupon.builder() //
                .id(1L) //
                .title("coupon") //
                .minPurchaseAmount(new BigDecimal(100)) //
                .build();

        CouponDTO couponDTO = CouponDTO.builder() //
                .id(1L) //
                .title("couponDTO") //
                .minPurchaseAmount(new BigDecimal(100)) //
                .build();

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(2L);

        Mockito.when(couponService.getCouponById(Mockito.any())).thenReturn(couponDTO);
        Mockito.when(shoppingCartRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(shoppingCart));
        Mockito.when(shoppingCartRepository.save(Mockito.any())).thenReturn(shoppingCart);
        Mockito.when(shoppingCartMapper.convertToDTO(Mockito.any())).thenReturn(new ShoppingCartDTO());

        shoppingCartService.applyCoupon(shoppingCart.getId(), coupon.getId());

        Mockito.verify(shoppingCartHelper).calculateCouponDiscount(Mockito.any(), Mockito.any());
        Mockito.verify(shoppingCartHelper).calculateDisplayPrice(Mockito.any());
    }

    @Test
    public void shouldApplyDelivery() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(2L);

        DeliveryCostDTO deliveryCostDTO = new DeliveryCostDTO(new BigDecimal("2"), new BigDecimal("2"));

        Mockito.when(shoppingCartRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(shoppingCart));
        Mockito.when(shoppingCartRepository.save(Mockito.any())).thenReturn(shoppingCart);
        Mockito.when(shoppingCartMapper.convertToDTO(Mockito.any())).thenReturn(new ShoppingCartDTO());

        shoppingCartService.applyDelivery(shoppingCart.getId(), deliveryCostDTO);

        Mockito.verify(deliveryHelper).calculateDelivery(Mockito.any(), Mockito.any());
        Mockito.verify(shoppingCartHelper).calculateDisplayPrice(Mockito.any());
    }
}