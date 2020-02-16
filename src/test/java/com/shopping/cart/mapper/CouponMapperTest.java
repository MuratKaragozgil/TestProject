package com.shopping.cart.mapper;

import com.shopping.cart.exception.GenericException;
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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Murat Karagözgil
 */
@RunWith(MockitoJUnitRunner.class)
public class CouponMapperTest {
    @InjectMocks
    private CouponMapper couponMapper;

    @Mock
    private CouponRepository couponRepository;

    private Coupon coupon;
    private CouponDTO couponDTO;

    @Before
    public void setup() {
        coupon = Coupon.builder()
                .id(10L) //
                .title("coupon") //
                .minPurchaseAmount(new BigDecimal(100)) //
                .discountAmount(new BigDecimal(50)) //
                .discountType(DiscountType.RATE) //
                .build();

        couponDTO = CouponDTO.builder()
                .id(10L) //
                .title("coupon") //
                .minPurchaseAmount(new BigDecimal(100)) //
                .discountAmount(new BigDecimal(50)) //
                .discountType(DiscountType.RATE) //
                .build();
    }

    @Test
    public void shouldToEntityReturnNull() {
        Coupon resultEntity = couponMapper.convertToEntity(null);
        assertThat(resultEntity, equalTo(null));
    }

    @Test
    public void shouldToEntityHasId() {
        Mockito.when(couponRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(coupon));
        Coupon resultEntity = couponMapper.convertToEntity(couponDTO);

        assertThat(resultEntity.getId(), equalTo(coupon.getId()));
        assertThat(resultEntity.getTitle(), equalTo(coupon.getTitle()));
        assertThat(resultEntity.getMinPurchaseAmount(), equalTo(coupon.getMinPurchaseAmount()));
        assertThat(resultEntity.getDiscountAmount(), equalTo(coupon.getDiscountAmount()));
        assertThat(resultEntity.getDiscountType(), equalTo(coupon.getDiscountType()));
    }

    @Test(expected = GenericException.class)
    public void shouldToEntityNotFoundCoupon() {
        Mockito.when(couponRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        couponMapper.convertToEntity(couponDTO);
    }

    @Test
    public void shouldToEntityNewCoupon() {
        couponDTO.setId(null);
        Coupon resultEntity = couponMapper.convertToEntity(couponDTO);
        assertThat(resultEntity.getId(), equalTo(null));
    }

    @Test
    public void shouldToDtoReturnNull() {
        CouponDTO resultDTO = couponMapper.convertToDTO(null);
        assertThat(resultDTO, equalTo(null));
    }

    @Test
    public void shouldToDto() {
        CouponDTO resultDTO = couponMapper.convertToDTO(coupon);

        assertThat(resultDTO.getId(), equalTo(couponDTO.getId()));
        assertThat(resultDTO.getTitle(), equalTo(couponDTO.getTitle()));
        assertThat(resultDTO.getMinPurchaseAmount(), equalTo(couponDTO.getMinPurchaseAmount()));
        assertThat(resultDTO.getDiscountAmount(), equalTo(couponDTO.getDiscountAmount()));
        assertThat(resultDTO.getDiscountType(), equalTo(couponDTO.getDiscountType()));
    }
}