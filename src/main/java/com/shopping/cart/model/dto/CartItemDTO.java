package com.shopping.cart.model.dto;

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
public class CartItemDTO {
    private Long id;
    private Long shoppingCartId;
    private Long productId;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private BigDecimal campaignDiscount;
    private BigDecimal finalPrice;
}
