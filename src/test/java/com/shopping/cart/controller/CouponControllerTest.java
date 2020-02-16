package com.shopping.cart.controller;

import com.shopping.cart.model.DiscountType;
import com.shopping.cart.model.dto.CouponDTO;
import com.shopping.cart.service.CouponService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author Murat Karagözgil
 */
@RunWith(MockitoJUnitRunner.class)
public class CouponControllerTest {

    @InjectMocks
    private CouponController couponController;

    @Mock
    private CouponService couponService;

    @Mock
    private MockHttpServletRequest request;

    private static final Long COUPON_ID = 1L;

    private static final CouponDTO COUPON_DTO = CouponDTO.builder()
            .id(COUPON_ID) //
            .discountAmount(new BigDecimal(2)) //
            .minPurchaseAmount(new BigDecimal(3)) //
            .discountType(DiscountType.AMOUNT)
            .title("Coupon") //
            .build();

    @Before
    public void setUp() {
        when(couponService.getCouponById(COUPON_ID)).thenReturn(COUPON_DTO);
        when(couponService.saveCoupon(COUPON_DTO)).thenReturn(COUPON_DTO);
    }

    @Test
    public void shouldReturnCorrectCouponWhenUsedCorrectParameters() {
        ResponseEntity<CouponDTO> couponDTOResponseEntity = couponController.getCouponById(request, COUPON_ID);
        assertThat(couponDTOResponseEntity.getBody(), equalTo(COUPON_DTO));
    }

    @Test
    public void shouldReturnSavedCouponWhenUsedCorrectParameters() {
        ResponseEntity<CouponDTO> couponDTOResponseEntity = couponController.saveCoupon(request, COUPON_DTO);
        assertThat(couponDTOResponseEntity.getBody(), equalTo(COUPON_DTO));
    }
}