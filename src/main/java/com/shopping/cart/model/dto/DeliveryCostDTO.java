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
public class DeliveryCostDTO {
    private BigDecimal costPerDelivery;
    private BigDecimal costPerProduct;
}
