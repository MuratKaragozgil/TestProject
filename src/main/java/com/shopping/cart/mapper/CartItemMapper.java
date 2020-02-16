package com.shopping.cart.mapper;

import com.shopping.cart.exception.CommonError;
import com.shopping.cart.exception.GenericException;
import com.shopping.cart.model.CartItem;
import com.shopping.cart.model.dto.CartItemDTO;
import com.shopping.cart.repository.CartItemRepository;
import com.shopping.cart.repository.ProductRepository;
import com.shopping.cart.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Murat Karagözgil
 */

@Component
public class CartItemMapper extends BaseMapper<CartItem, CartItemDTO> {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public CartItem convertToEntity(CartItemDTO cartItemDTO) {
        if (cartItemDTO == null) return null;

        CartItem cartItem = null;

        if (cartItemDTO.getId() != null) {
            cartItem = cartItemRepository.findById(cartItemDTO.getId()).orElseThrow(new GenericException(CommonError.CART_ITEM_NOT_FOUND));
        }

        if (cartItem == null) {
            cartItem = new CartItem();
        }

        if (cartItemDTO.getShoppingCartId() != null) {
            cartItem.setShoppingCart(shoppingCartRepository.findById(cartItemDTO.getShoppingCartId()).orElseThrow(new GenericException(CommonError.SHOPPING_CART_NOT_FOUND)));
        }

        if (cartItemDTO.getProductId() != null) {
            cartItem.setProduct(productRepository.findById(cartItemDTO.getProductId()).orElseThrow(new GenericException(CommonError.PRODUCT_NOT_FOUND)));
        }

        cartItem.setQuantity(cartItemDTO.getQuantity());
        cartItem.setUnitPrice(cartItemDTO.getUnitPrice());
        cartItem.setTotalPrice(cartItemDTO.getTotalPrice());
        cartItem.setCampaignDiscount(cartItemDTO.getCampaignDiscount());
        cartItem.setFinalPrice(cartItemDTO.getFinalPrice());

        return cartItem;
    }

    @Override
    public CartItemDTO convertToDTO(CartItem cartItem) {
        if (cartItem == null) return null;

        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setId(cartItem.getId());
        cartItemDTO.setShoppingCartId(cartItem.getShoppingCart().getId());
        cartItemDTO.setProductId(cartItem.getProduct().getId());
        cartItemDTO.setQuantity(cartItem.getQuantity());
        cartItemDTO.setUnitPrice(cartItem.getUnitPrice());
        cartItemDTO.setTotalPrice(cartItem.getTotalPrice());
        cartItemDTO.setCampaignDiscount(cartItem.getCampaignDiscount());
        cartItemDTO.setFinalPrice(cartItem.getFinalPrice());

        return cartItemDTO;
    }
}