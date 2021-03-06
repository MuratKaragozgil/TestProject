package com.shopping.cart.request;

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
public class ItemRequest {
    private Long productId;
    private Long shoppingCartId;
}
