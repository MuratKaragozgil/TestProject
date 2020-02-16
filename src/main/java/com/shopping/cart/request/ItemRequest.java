package com.shopping.cart.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Murat Karagözgil
 */
@Getter
@Setter
@ToString
public class ItemRequest {
    private Long productId;
    private Long shoppingCartId;
}
