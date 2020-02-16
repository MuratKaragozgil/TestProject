package com.shopping.cart.controller;

import com.shopping.cart.model.dto.ShoppingCartDTO;
import com.shopping.cart.request.CampaignRequest;
import com.shopping.cart.request.CouponRequest;
import com.shopping.cart.request.ItemRequest;
import com.shopping.cart.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Murat Karagözgil
 */
@Controller
@RequestMapping("shopping-cart")
public class ShoppingCartController extends BaseController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("save")
    public ResponseEntity<ShoppingCartDTO> saveShoppingCart(HttpServletRequest request) {
        logUserAction(request, "saveShoppingCart");

        ShoppingCartDTO shoppingCartDTO = shoppingCartService.saveShoppingCart();

        return ResponseEntity.ok(shoppingCartDTO);
    }

    @GetMapping("print/{shoppingCartId}")
    public ResponseEntity<ShoppingCartDTO> getShoppingCartById(HttpServletRequest request, @PathVariable("shoppingCartId") Long id) {
        logUserAction(request, "GetShoppingCartById::{}", id);

        ShoppingCartDTO shoppingCart = shoppingCartService.getShoppingCartById(id);

        return ResponseEntity.ok(shoppingCart);
    }

    @PostMapping("add-item")
    public ResponseEntity<ShoppingCartDTO> addItem(HttpServletRequest request, @RequestBody ItemRequest itemRequest) {
        logUserAction(request, "AddItemRequest::{}", itemRequest);

        ShoppingCartDTO shoppingCartDTO = shoppingCartService.addItem(itemRequest.getShoppingCartId(), itemRequest.getProductId());

        return ResponseEntity.ok(shoppingCartDTO);
    }

    @PostMapping("remove-item")
    public ResponseEntity<ShoppingCartDTO> removeItem(HttpServletRequest request, @RequestBody ItemRequest itemRequest) {
        logUserAction(request, "RemoveItemRequest::{}", itemRequest);

        ShoppingCartDTO shoppingCartDTO = shoppingCartService.removeItem(itemRequest.getShoppingCartId(), itemRequest.getProductId());

        return ResponseEntity.ok(shoppingCartDTO);
    }

    @PostMapping("apply-campaign")
    public ResponseEntity<ShoppingCartDTO> applyCampaign(HttpServletRequest request, @RequestBody CampaignRequest campaignRequest) {
        logUserAction(request, "ApplyCampaign::{}", campaignRequest);

        ShoppingCartDTO shoppingCartDTO = shoppingCartService.applyCampaign(campaignRequest.getShoppingCartId(), campaignRequest.getCampaignId());

        return ResponseEntity.ok(shoppingCartDTO);
    }

    @PostMapping("apply-coupon")
    public ResponseEntity<ShoppingCartDTO> applyCoupon(HttpServletRequest request, @RequestBody CouponRequest couponRequest) {
        logUserAction(request, "ApplyCoupon::{}", couponRequest);

        ShoppingCartDTO shoppingCartDTO = shoppingCartService.applyCoupon(couponRequest.getShoppingCartId(), couponRequest.getCouponId());

        return ResponseEntity.ok(shoppingCartDTO);
    }
}