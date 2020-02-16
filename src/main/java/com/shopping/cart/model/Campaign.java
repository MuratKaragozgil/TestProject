package com.shopping.cart.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Murat Karagözgil
 */
@Entity
@Table(name = "CAMPAIGN")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Campaign {

    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_TITLE = "TITLE";
    public static final String COLUMN_CATEGORY_ID = "CATEGORY_ID";
    public static final String COLUMN_DISCOUNT_AMOUNT = "DISCOUNT_AMOUNT";
    public static final String COLUMN_ITEM_LIMIT = "ITEM_LIMIT";
    public static final String COLUMN_DISCOUNT_TYPE = "DISCOUNT_TYPE";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = COLUMN_ID)
    private Long id;

    @Column(name = COLUMN_TITLE)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_CATEGORY_ID, referencedColumnName = COLUMN_ID)
    private Category category;

    @Column(name = COLUMN_DISCOUNT_AMOUNT)
    private BigDecimal discountAmount;

    @Column(name = COLUMN_ITEM_LIMIT)
    private Integer itemLimit;

    @Enumerated(EnumType.STRING)
    @Column(name = COLUMN_DISCOUNT_TYPE)
    private DiscountType discountType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Campaign campaign = (Campaign) o;

        return Objects.equals(id, campaign.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
