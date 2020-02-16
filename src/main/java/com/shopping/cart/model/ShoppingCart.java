package com.shopping.cart.model;

import com.shopping.cart.utils.MathUtil;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Murat Karagözgil
 */

@Entity
@Table(name = "SHOPPING_CART")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {

    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_COUPON_DISCOUNT = "COUPON_DISCOUNT";
    private static final String COLUMN_CAMPAIGN_DISCOUNT = "CAMPAOGN_DISCOUNT";
    private static final String COLUMN_DELIVERY_COST = "DELIVERY_COST";
    private static final String COLUMN_TOTAL_FINAL_PRICE = "TOTAL_FINAL_PRICE";
    private static final String COLUMN_DISPLAY_PRICE = "DISPLAY_PRICE";

    @Id
    @Column(name = COLUMN_ID)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "shoppingCart", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private Set<CartItem> cartItems = new HashSet<>();

    @Column(name = COLUMN_COUPON_DISCOUNT)
    @Builder.Default
    private BigDecimal couponDiscount = BigDecimal.ZERO;

    @Column(name = COLUMN_DELIVERY_COST)
    @Builder.Default
    private BigDecimal deliveryCost = BigDecimal.ZERO;

    @Column(name = COLUMN_CAMPAIGN_DISCOUNT)
    @Builder.Default
    private BigDecimal campaignDiscount = BigDecimal.ZERO;

    @Column(name = COLUMN_TOTAL_FINAL_PRICE)
    @Builder.Default
    private BigDecimal totalPrice = BigDecimal.ZERO;

    @Column(name = COLUMN_DISPLAY_PRICE)
    @Builder.Default
    private BigDecimal displayPrice = BigDecimal.ZERO;

    public boolean isUsedCampaignDiscount() {
        return getCartItems().stream().anyMatch(p -> MathUtil.isBigThanZero(p.getCampaignDiscount()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCart that = (ShoppingCart) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
