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
public class CouponRequest {
    private Long couponId;
    private Long shoppingCartId;
}
