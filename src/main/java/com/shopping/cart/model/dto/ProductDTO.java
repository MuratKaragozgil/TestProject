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
public class ProductDTO {
    private Long id;
    private String title;
    private BigDecimal price;
    private Long categoryId;
}
