package com.shopping.cart.model.dto;

import com.shopping.cart.model.DiscountType;
import lombok.*;

import java.math.BigDecimal;

/**
 * @author Murat Karagözgil
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponDTO {
    private Long id;
    private String title;
    private BigDecimal minPurchaseAmount;
    private BigDecimal discountAmount;
    private DiscountType discountType;
}
