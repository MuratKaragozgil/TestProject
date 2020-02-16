package com.shopping.cart.model.dto;

import lombok.*;

/**
 * @author Murat Karagözgil
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Long id;
    private String title;
    private Long parentCategoryId;
}
