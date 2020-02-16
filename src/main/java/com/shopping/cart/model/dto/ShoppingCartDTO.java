package com.shopping.cart.model.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Murat Karagözgil
 */

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartDTO {
    private Long id;
    @Builder.Default
    private Set<CartItemDTO> cartItems = new HashSet<>();
    @Builder.Default
    private BigDecimal couponDiscount = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal deliveryCost = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal totalPrice = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal displayPrice = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal campaignDiscount = BigDecimal.ZERO;
}
