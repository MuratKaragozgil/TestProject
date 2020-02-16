package com.shopping.cart.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Murat Karagözgil
 */
@Entity
@Table(name = "PRODUCT")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_TITLE = "TITLE";
    private static final String COLUMN_PRICE = "PRICE";
    private static final String COLUMN_CATEGORY_ID = "CATEGORY_ID";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = COLUMN_ID)
    private Long id;

    @Column(name = COLUMN_TITLE)
    private String title;

    @Column(name = COLUMN_PRICE)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_CATEGORY_ID, referencedColumnName = COLUMN_ID)
    private Category category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}
