package com.shopping.cart.service.impl;

import com.shopping.cart.exception.CommonError;
import com.shopping.cart.exception.GenericException;
import com.shopping.cart.helper.CartItemHelper;
import com.shopping.cart.helper.DeliveryHelper;
import com.shopping.cart.helper.ShoppingCartHelper;
import com.shopping.cart.mapper.ShoppingCartMapper;
import com.shopping.cart.model.CartItem;
import com.shopping.cart.model.Product;
import com.shopping.cart.model.ShoppingCart;
import com.shopping.cart.model.dto.CampaignDTO;
import com.shopping.cart.model.dto.CouponDTO;
import com.shopping.cart.model.dto.DeliveryCostDTO;
import com.shopping.cart.model.dto.ShoppingCartDTO;
import com.shopping.cart.repository.CartItemRepository;
import com.shopping.cart.repository.ProductRepository;
import com.shopping.cart.repository.ShoppingCartRepository;
import com.shopping.cart.service.CampaignService;
import com.shopping.cart.service.CouponService;
import com.shopping.cart.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

/**
 * @author Murat Karagözgil
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CouponService couponService;

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemHelper cartItemHelper;

    @Autowired
    private ShoppingCartHelper shoppingCartHelper;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private DeliveryHelper deliveryHelper;

    @Override
    public ShoppingCartDTO saveShoppingCart() {
        return shoppingCartMapper.convertToDTO(shoppingCartRepository.save(new ShoppingCart()));
    }

    @Override
    public ShoppingCartDTO getShoppingCartById(Long id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id).orElseThrow(new GenericException(CommonError.SHOPPING_CART_NOT_FOUND));
        return shoppingCartMapper.convertToDTO(shoppingCart);
    }

    @Override
    public ShoppingCartDTO addItem(Long shoppingCartId, Long productId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartId).orElseThrow(new GenericException(CommonError.SHOPPING_CART_NOT_FOUND));

        final Product product = productRepository.findById(productId).orElseThrow(new GenericException(CommonError.PRODUCT_NOT_FOUND));

        CartItem cartItem = shoppingCart.getCartItems().stream().filter(p -> p.getProduct().getId().equals(product.getId())).findAny().orElse(null);

        if (cartItem != null) {
            incrementQuantityAndCalculatePrice(cartItem);
        } else {
            cartItem = addNewCartItem(product, shoppingCart);
            shoppingCart.getCartItems().add(cartItem);
        }

        ShoppingCart result = updateShoppingCartPriceData(shoppingCart);
        return shoppingCartMapper.convertToDTO(result);
    }

    private CartItem addNewCartItem(Product product, ShoppingCart shoppingCart) {
        CartItem cartItem = new CartItem();
        cartItem.setShoppingCart(shoppingCart);
        cartItem.setQuantity(1);
        cartItem.setProduct(product);
        cartItem.setUnitPrice(product.getPrice());
        cartItem.setTotalPrice(product.getPrice());
        cartItemHelper.calculateFinalPrice(cartItem);
        return cartItem;
    }

    private void incrementQuantityAndCalculatePrice(CartItem cartItem) {
        cartItem.incrementQuantity();

        BigDecimal totalPrice = cartItem.getUnitPrice().multiply(new BigDecimal(cartItem.getQuantity()));
        cartItem.setTotalPrice(totalPrice);

        cartItemHelper.calculateFinalPrice(cartItem);
    }

    @Override
    public ShoppingCart updateShoppingCartPriceData(ShoppingCart shoppingCart) {
        shoppingCartHelper.calculateDisplayPrice(shoppingCart);
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCartDTO removeItem(Long shoppingCartId, Long productId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartId).orElseThrow(new GenericException(CommonError.SHOPPING_CART_NOT_FOUND));
        CartItem cartItem = shoppingCart.getCartItems().stream() //
                .filter(p -> p.getProduct().getId().equals(productId)).findAny() //
                .orElseThrow(new GenericException(CommonError.CART_ITEM_NOT_FOUND));

        cartItem.decrementQuantity();

        if (cartItem.isEmptyCartItem()) {
            shoppingCart.getCartItems().remove(cartItem);
            cartItemRepository.delete(cartItem);
        } else {
            BigDecimal totalPrice = cartItem.getUnitPrice().multiply(new BigDecimal(cartItem.getQuantity()));
            cartItem.setTotalPrice(totalPrice);

            cartItemHelper.calculateFinalPrice(cartItem);
        }

        ShoppingCart result = updateShoppingCartPriceData(shoppingCart);
        return shoppingCartMapper.convertToDTO(result);
    }

    @Override
    public ShoppingCartDTO applyCoupon(Long shoppingCartId, Long couponId) {
        CouponDTO couponDto = couponService.getCouponById(couponId);
        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartId).orElseThrow(new GenericException(CommonError.SHOPPING_CART_NOT_FOUND));

        shoppingCartHelper.calculateCouponDiscount(shoppingCart, couponDto);
        ShoppingCart result = updateShoppingCartPriceData(shoppingCart);

        return shoppingCartMapper.convertToDTO(result);
    }

    @Override
    public ShoppingCartDTO applyCampaign(Long shoppingCartId, Long campaignId) {
        CampaignDTO campaign = campaignService.getCampaignById(campaignId);
        Set<CartItem> cartItems = cartItemRepository.findByCategoryId(campaign.getCategoryId());

        for (CartItem cartItem : cartItems) {
            cartItemHelper.calculateCampaignDiscount(cartItem, campaign);
        }

        cartItemRepository.saveAll(cartItems);

        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartId).orElseThrow(new GenericException(CommonError.SHOPPING_CART_NOT_FOUND));
        ShoppingCart result = updateShoppingCartPriceData(shoppingCart);

        return shoppingCartMapper.convertToDTO(result);
    }

    @Override
    public ShoppingCartDTO applyDelivery(Long shoppingCartId, DeliveryCostDTO deliveryCostDTO) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartId).orElseThrow(new GenericException(CommonError.SHOPPING_CART_NOT_FOUND));
        deliveryHelper.calculateDelivery(shoppingCart, deliveryCostDTO);

        ShoppingCart result = updateShoppingCartPriceData(shoppingCart);
        return shoppingCartMapper.convertToDTO(result);
    }
}