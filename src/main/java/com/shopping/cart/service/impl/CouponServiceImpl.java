package com.shopping.cart.service.impl;

import com.shopping.cart.exception.CommonError;
import com.shopping.cart.exception.GenericException;
import com.shopping.cart.mapper.CouponMapper;
import com.shopping.cart.model.Coupon;
import com.shopping.cart.model.dto.CouponDTO;
import com.shopping.cart.repository.CouponRepository;
import com.shopping.cart.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Murat Karagözgil
 */
@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private CouponMapper couponMapper;

    @Override
    public CouponDTO getCouponById(Long id) {
        Coupon coupon = couponRepository.findById(id).orElseThrow(new GenericException(CommonError.COUPON_NOT_FOUND));
        return couponMapper.convertToDTO(coupon);
    }

    @Override
    public CouponDTO saveCoupon(CouponDTO couponDTO) {
        Coupon savedCoupon = couponRepository.save(couponMapper.convertToEntity(couponDTO));
        return couponMapper.convertToDTO(savedCoupon);
    }
}
