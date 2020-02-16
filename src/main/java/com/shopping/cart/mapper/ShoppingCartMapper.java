package com.shopping.cart.mapper;

import com.shopping.cart.exception.CommonError;
import com.shopping.cart.exception.GenericException;
import com.shopping.cart.model.ShoppingCart;
import com.shopping.cart.model.dto.ShoppingCartDTO;
import com.shopping.cart.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * @author Murat Karagözgil
 */

@Component
public class ShoppingCartMapper extends BaseMapper<ShoppingCart, ShoppingCartDTO> {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private CartItemMapper cartItemMapper;

    @Override
    public ShoppingCart convertToEntity(ShoppingCartDTO shoppingCartDTO) {
        if (shoppingCartDTO == null) return null;

        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartDTO.getId()).orElseThrow(new GenericException(CommonError.SHOPPING_CART_NOT_FOUND));

        if (!CollectionUtils.isEmpty(shoppingCartDTO.getCartItems())) {
            shoppingCart.setCartItems(cartItemMapper.convertToEntitySet(shoppingCartDTO.getCartItems()));
        }

        shoppingCart.setCouponDiscount(shoppingCartDTO.getCouponDiscount());
        shoppingCart.setDeliveryCost(shoppingCartDTO.getDeliveryCost());
        shoppingCart.setTotalPrice(shoppingCartDTO.getTotalPrice());
        shoppingCart.setDisplayPrice(shoppingCartDTO.getDisplayPrice());
        shoppingCart.setCampaignDiscount(shoppingCartDTO.getCampaignDiscount());

        return shoppingCart;
    }

    @Override
    public ShoppingCartDTO convertToDTO(ShoppingCart shoppingCart) {
        if (shoppingCart == null) return null;

        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();

        if (!CollectionUtils.isEmpty(shoppingCart.getCartItems())) {
            shoppingCartDTO.setCartItems(cartItemMapper.convertToDTOSet(shoppingCart.getCartItems()));
        }

        shoppingCartDTO.setId(shoppingCart.getId());
        shoppingCartDTO.setCouponDiscount(shoppingCart.getCouponDiscount());
        shoppingCartDTO.setDeliveryCost(shoppingCart.getDeliveryCost());
        shoppingCartDTO.setTotalPrice(shoppingCart.getTotalPrice());
        shoppingCartDTO.setDisplayPrice(shoppingCart.getDisplayPrice());
        shoppingCartDTO.setCampaignDiscount(shoppingCart.getCampaignDiscount());

        return shoppingCartDTO;
    }
}
