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
public class CampaignDTO {
    private Long id;
    private String title;
    private Long categoryId;
    private BigDecimal discountAmount;
    private Integer itemLimit;
    private DiscountType discountType;
}
