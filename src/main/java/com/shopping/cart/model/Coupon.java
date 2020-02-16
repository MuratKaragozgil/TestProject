package com.shopping.cart.model;

import com.shopping.cart.utils.MathUtil;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Murat Karagözgil
 */

@Entity
@Table(name = "COUPON")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {

    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_TITLE = "TITLE";
    public static final String COLUMN_MIN_PURCHASE_AMOUNT = "MIN_PURCHASE_AMOUNT";
    public static final String COLUMN_DISCOUNT_AMOUNT = "DISCOUNT_AMOUNT";
    public static final String COLUMN_DISCOUNT_TYPE = "DISCOUNT_TYPE";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = COLUMN_ID)
    private Long id;

    @Column(name = COLUMN_TITLE)
    private String title;

    @Column(name = COLUMN_MIN_PURCHASE_AMOUNT)
    private BigDecimal minPurchaseAmount;

    @Column(name = COLUMN_DISCOUNT_AMOUNT)
    private BigDecimal discountAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = COLUMN_DISCOUNT_TYPE)
    private DiscountType discountType;

    public boolean isUsableCoupon(BigDecimal totalFinalPrice) {
        return MathUtil.isBig(totalFinalPrice, minPurchaseAmount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coupon coupon = (Coupon) o;

        return Objects.equals(id, coupon.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
