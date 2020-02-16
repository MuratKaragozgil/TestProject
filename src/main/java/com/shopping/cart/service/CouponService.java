package com.shopping.cart.service;

import com.shopping.cart.model.dto.CouponDTO;

/**
 * @author Murat Karagözgil
 */
public interface CouponService {

    CouponDTO getCouponById(Long id);

    CouponDTO saveCoupon(CouponDTO couponDTO);
}
