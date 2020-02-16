package com.shopping.cart.mapper;

import com.shopping.cart.exception.CommonError;
import com.shopping.cart.exception.GenericException;
import com.shopping.cart.model.Coupon;
import com.shopping.cart.model.dto.CouponDTO;
import com.shopping.cart.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Murat Karagözgil
 */
@Component
public class CouponMapper extends BaseMapper<Coupon, CouponDTO> {

    @Autowired
    private CouponRepository couponRepository;

    @Override
    public Coupon convertToEntity(CouponDTO couponDTO) {
        if (couponDTO == null) return null;

        Coupon coupon = null;

        if (couponDTO.getId() != null) {
            coupon = couponRepository.findById(couponDTO.getId()).orElseThrow(new GenericException(CommonError.COUPON_NOT_FOUND));
        }

        if (coupon == null) coupon = new Coupon();

        coupon.setTitle(couponDTO.getTitle());
        coupon.setMinPurchaseAmount(couponDTO.getMinPurchaseAmount());
        coupon.setDiscountAmount(couponDTO.getDiscountAmount());
        coupon.setDiscountType(couponDTO.getDiscountType());

        return coupon;
    }

    @Override
    public CouponDTO convertToDTO(Coupon coupon) {
        if (coupon == null) return null;

        CouponDTO couponDTO = new CouponDTO();
        couponDTO.setId(coupon.getId());
        couponDTO.setTitle(coupon.getTitle());
        couponDTO.setMinPurchaseAmount(coupon.getMinPurchaseAmount());
        couponDTO.setDiscountAmount(coupon.getDiscountAmount());
        couponDTO.setDiscountType(coupon.getDiscountType());

        return couponDTO;
    }
}
