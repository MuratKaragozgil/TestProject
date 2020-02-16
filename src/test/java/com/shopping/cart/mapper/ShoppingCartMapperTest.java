package com.shopping.cart.mapper;

import com.shopping.cart.model.CartItem;
import com.shopping.cart.model.ShoppingCart;
import com.shopping.cart.model.dto.CartItemDTO;
import com.shopping.cart.model.dto.ShoppingCartDTO;
import com.shopping.cart.repository.ShoppingCartRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Murat Karagözgil
 */
@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartMapperTest {

    @InjectMocks
    private ShoppingCartMapper shoppingCartMapper;

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @Mock
    private CartItemMapper cartItemMapper;

    private ShoppingCart shoppingCart;
    private ShoppingCartDTO shoppingCartDTO;

    @Before
    public void setup() {
        shoppingCart = new ShoppingCart();
        shoppingCart.setId(1L);

        shoppingCartDTO = new ShoppingCartDTO();
        shoppingCartDTO.setId(1L);
    }

    @Test
    public void shouldToEntityReturnNull() {
        ShoppingCart resultEntity = shoppingCartMapper.convertToEntity(null);
        assertThat(resultEntity, equalTo(null));
    }

    @Test
    public void shouldToEntity() {
        Mockito.when(shoppingCartRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(shoppingCart));
        ShoppingCart resultEntity = shoppingCartMapper.convertToEntity(shoppingCartDTO);

        assertThat(resultEntity.getId(), equalTo(shoppingCart.getId()));
        assertThat(resultEntity.getCartItems(), equalTo(shoppingCart.getCartItems()));
        assertThat(resultEntity.getCouponDiscount(), equalTo(shoppingCart.getCouponDiscount()));
        assertThat(resultEntity.getDeliveryCost(), equalTo(shoppingCart.getDeliveryCost()));
        assertThat(resultEntity.getTotalPrice(), equalTo(shoppingCart.getTotalPrice()));
        assertThat(resultEntity.getDisplayPrice(), equalTo(shoppingCart.getDisplayPrice()));
    }

    @Test
    public void shouldToEntityHasCartItem() {
        CartItem cartItem = CartItem.builder().id(10L).shoppingCart(shoppingCart).quantity(1).build();
        CartItemDTO cartItemDTO = CartItemDTO.builder().id(10L).shoppingCartId(shoppingCart.getId()).build();
        shoppingCartDTO.setCartItems(new HashSet<>(Collections.singletonList(cartItemDTO)));

        Mockito.when(cartItemMapper.convertToEntitySet(Mockito.any())).thenReturn(new HashSet<>(Collections.singletonList(cartItem)));
        Mockito.when(shoppingCartRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(shoppingCart));

        ShoppingCart resultEntity = shoppingCartMapper.convertToEntity(shoppingCartDTO);

        assertThat(resultEntity.getCartItems().size(), equalTo(1));
        assertThat(resultEntity.getCartItems().stream().anyMatch(p -> p.getId().equals(cartItem.getId())), equalTo(true));
    }

    @Test
    public void shouldToDtoReturnNull() {
        ShoppingCartDTO resultDTO = shoppingCartMapper.convertToDTO(null);
        assertThat(resultDTO, equalTo(null));
    }

    @Test
    public void shouldToDto() {
        ShoppingCartDTO resultDTO = shoppingCartMapper.convertToDTO(shoppingCart);

        assertThat(resultDTO.getId(), equalTo(shoppingCart.getId()));
        assertThat(resultDTO.getCartItems(), equalTo(shoppingCart.getCartItems()));
        assertThat(resultDTO.getCouponDiscount(), equalTo(shoppingCart.getCouponDiscount()));
        assertThat(resultDTO.getDeliveryCost(), equalTo(shoppingCart.getDeliveryCost()));
        assertThat(resultDTO.getTotalPrice(), equalTo(shoppingCart.getTotalPrice()));
        assertThat(resultDTO.getDisplayPrice(), equalTo(shoppingCart.getDisplayPrice()));
    }

    @Test
    public void shouldToDtoHasCartItem() {
        CartItem cartItem = CartItem.builder().id(10L).shoppingCart(shoppingCart).quantity(1).build();
        CartItemDTO cartItemDTO = CartItemDTO.builder().id(10L).shoppingCartId(shoppingCart.getId()).build();
        shoppingCart.setCartItems(new HashSet<>(Collections.singletonList(cartItem)));

        Mockito.when(cartItemMapper.convertToDTOSet(Mockito.any())).thenReturn(new HashSet<>(Collections.singletonList(cartItemDTO)));
        ShoppingCartDTO resultDTO = shoppingCartMapper.convertToDTO(shoppingCart);

        assertThat(resultDTO.getCartItems().size(), equalTo(1));
        assertThat(resultDTO.getCartItems().stream().anyMatch(p -> p.getId().equals(cartItemDTO.getId())), equalTo(true));
    }
}