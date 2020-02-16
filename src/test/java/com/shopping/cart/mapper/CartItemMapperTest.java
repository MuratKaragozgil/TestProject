package com.shopping.cart.mapper;

import com.shopping.cart.exception.GenericException;
import com.shopping.cart.model.CartItem;
import com.shopping.cart.model.Product;
import com.shopping.cart.model.ShoppingCart;
import com.shopping.cart.model.dto.CartItemDTO;
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
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Murat Karagözgil
 */
@RunWith(MockitoJUnitRunner.class)
public class CartItemMapperTest {

    @InjectMocks
    private CartItemMapper cartItemMapper;

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @Mock
    private ProductRepository productRepository;

    private CartItem cartItem;
    private CartItemDTO cartItemDTO;
    private ShoppingCart shoppingCart;
    private Product product;

    @Before
    public void setup() {
        BigDecimal price = new BigDecimal("500");

        product = Product.builder().id(1L).title("product").price(price).build();
        shoppingCart = new ShoppingCart();
        shoppingCart.setId(1L);

        cartItem = CartItem.builder().id(10L).shoppingCart(shoppingCart).product(product).quantity(1)
                .unitPrice(price).totalPrice(price).campaignDiscount(BigDecimal.ZERO).finalPrice(price).build();
        cartItemDTO = CartItemDTO.builder().id(10L).shoppingCartId(shoppingCart.getId()).productId(product.getId()).quantity(1)
                .unitPrice(price).totalPrice(price).campaignDiscount(BigDecimal.ZERO).finalPrice(price).build();
    }

    @Test
    public void shouldToEntityReturnNull() {
        CartItem resultDTO = cartItemMapper.convertToEntity(null);
        assertThat(resultDTO, equalTo(null));
    }

    @Test
    public void shouldToEntityHasId() {
        Mockito.when(cartItemRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(cartItem));
        Mockito.when(shoppingCartRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(shoppingCart));
        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(product));

        CartItem resultEntity = cartItemMapper.convertToEntity(cartItemDTO);

        assertThat(resultEntity.getId(), equalTo(cartItem.getId()));
        assertThat(resultEntity.getShoppingCart(), equalTo(cartItem.getShoppingCart()));
        assertThat(resultEntity.getProduct(), equalTo(cartItem.getProduct()));
        assertThat(resultEntity.getQuantity(), equalTo(cartItem.getQuantity()));
        assertThat(resultEntity.getUnitPrice(), equalTo(cartItem.getUnitPrice()));
        assertThat(resultEntity.getTotalPrice(), equalTo(cartItem.getTotalPrice()));
        assertThat(resultEntity.getTotalPrice(), equalTo(cartItem.getTotalPrice()));
    }

    @Test(expected = GenericException.class)
    public void shouldToEntityNotFoundCartItem() {
        Mockito.when(cartItemRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        cartItemMapper.convertToEntity(cartItemDTO);
    }

    @Test
    public void shouldToEntityNewCartItem() {
        cartItemDTO.setId(null);
        Mockito.when(shoppingCartRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(shoppingCart));
        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(product));

        CartItem resultEntity = cartItemMapper.convertToEntity(cartItemDTO);
        assertThat(resultEntity.getId(), equalTo(null));
    }

    @Test(expected = GenericException.class)
    public void shouldToEntityNullShoppingCart() {
        Mockito.when(cartItemRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(cartItem));
        Mockito.when(shoppingCartRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        cartItemMapper.convertToEntity(cartItemDTO);
    }

    @Test(expected = GenericException.class)
    public void shouldToEntityNullProduct() {
        Mockito.when(cartItemRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(cartItem));
        Mockito.when(shoppingCartRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(shoppingCart));
        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        cartItemMapper.convertToEntity(cartItemDTO);
    }

    @Test
    public void shouldToDtoReturnNull() {
        CartItemDTO resultDTO = cartItemMapper.convertToDTO(null);
        assertThat(resultDTO, equalTo(null));
    }

    @Test
    public void shouldToDto() {
        CartItemDTO resultDTO = cartItemMapper.convertToDTO(cartItem);

        assertThat(resultDTO.getId(), equalTo(cartItemDTO.getId()));
        assertThat(resultDTO.getShoppingCartId(), equalTo(cartItemDTO.getShoppingCartId()));
        assertThat(resultDTO.getProductId(), equalTo(cartItemDTO.getProductId()));
        assertThat(resultDTO.getQuantity(), equalTo(cartItemDTO.getQuantity()));
        assertThat(resultDTO.getUnitPrice(), equalTo(cartItemDTO.getUnitPrice()));
        assertThat(resultDTO.getTotalPrice(), equalTo(cartItemDTO.getTotalPrice()));
        assertThat(resultDTO.getTotalPrice(), equalTo(cartItemDTO.getTotalPrice()));
    }
}