package com.shopping.cart.service.impl;

import com.shopping.cart.mapper.CouponMapper;
import com.shopping.cart.model.Coupon;
import com.shopping.cart.model.DiscountType;
import com.shopping.cart.model.dto.CouponDTO;
import com.shopping.cart.repository.CouponRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * @author Murat Karagözgil
 */
@RunWith(MockitoJUnitRunner.class)
public class CouponServiceImplTest {

    @InjectMocks
    private CouponServiceImpl couponService;

    @Mock
    private CouponRepository couponRepository;

    @Mock
    private CouponMapper couponMapper;

    private Coupon coupon;
    private CouponDTO couponDTO;

    @Before
    public void setup() {
        coupon = Coupon.builder()
                .id(1L) //
                .title("coupon") //
                .minPurchaseAmount(new BigDecimal(100)) //
                .discountAmount(new BigDecimal(50)) //
                .discountType(DiscountType.RATE) //
                .build();

        couponDTO = CouponDTO.builder()
                .id(1L) //
                .title("coupon") //
                .minPurchaseAmount(new BigDecimal(100)) //
                .discountAmount(new BigDecimal(50)) //
                .discountType(DiscountType.RATE) //
                .build();
    }

    @Test
    public void shouldFindCouponById() {
        Mockito.when(couponRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(coupon));
        Mockito.when(couponMapper.convertToDTO(Mockito.any())).thenReturn(couponDTO);

        couponService.getCouponById(1L);
    }

    @Test
    public void shouldSave() {
        Mockito.when(couponMapper.convertToEntity(Mockito.any())).thenReturn(coupon);
        Mockito.when(couponRepository.save(Mockito.any())).thenReturn(coupon);
        Mockito.when(couponMapper.convertToDTO(Mockito.any())).thenReturn(couponDTO);

        couponService.saveCoupon(couponDTO);
    }
}