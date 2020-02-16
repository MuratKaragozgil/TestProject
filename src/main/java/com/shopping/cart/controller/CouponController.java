package com.shopping.cart.controller;

import com.shopping.cart.model.dto.CouponDTO;
import com.shopping.cart.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Murat Karagözgil
 */
@Controller
@RequestMapping("coupon")
public class CouponController extends BaseController {

    @Autowired
    private CouponService couponService;

    @GetMapping("get/{couponId}")
    public ResponseEntity<CouponDTO> getCouponById(HttpServletRequest request, @PathVariable("couponId") Long couponId) {
        logUserAction(request, "GetCouponById::{}", couponId);

        CouponDTO couponDTO = couponService.getCouponById(couponId);

        return ResponseEntity.ok(couponDTO);
    }

    @PostMapping("save")
    public ResponseEntity<CouponDTO> saveCoupon(HttpServletRequest request, @RequestBody CouponDTO couponDTO) {
        logUserAction(request, "SaveCouponDto::{}", couponDTO);

        CouponDTO savedCoupon = couponService.saveCoupon(couponDTO);

        return ResponseEntity.ok(savedCoupon);
    }
}
