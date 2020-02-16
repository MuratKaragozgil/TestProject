package com.shopping.cart.request;

import lombok.*;

/**
 * @author Murat Karagözgil
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CampaignRequest {
    private Long campaignId;
    private Long shoppingCartId;
}
