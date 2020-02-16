package com.shopping.cart.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Murat Karagözgil
 */
@Entity
@Table(name = "CART_ITEM")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_SHOPPING_CART_ID = "SHOPPING_CART_ID";
    private static final String COLUMN_PRODUCT_ID = "PRODUCT_ID";
    private static final String COLUMN_QUANTITY = "QUANTITY";
    private static final String COLUMN_UNIT_PRICE = "UNIT_PRICE";
    private static final String COLUMN_TOTAL_PRICE = "TOTAL_PRICE";
    private static final String COLUMN_CAMPAIGN_DISCOUNT = "CAMPAIGN_DISCOUNT";
    private static final String COLUMN_FINAL_PRICE = "FINAL_PRICE";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = COLUMN_ID)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_SHOPPING_CART_ID, referencedColumnName = COLUMN_ID)
    private ShoppingCart shoppingCart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_PRODUCT_ID, referencedColumnName = COLUMN_ID)
    private Product product;

    @Column(name = COLUMN_QUANTITY)
    private int quantity;

    @Column(name = COLUMN_UNIT_PRICE)
    private BigDecimal unitPrice;

    @Column(name = COLUMN_TOTAL_PRICE)
    private BigDecimal totalPrice;

    @Column(name = COLUMN_CAMPAIGN_DISCOUNT)
    @Builder.Default
    private BigDecimal campaignDiscount = BigDecimal.ZERO;

    @Column(name = COLUMN_FINAL_PRICE)
    @Builder.Default
    private BigDecimal finalPrice = BigDecimal.ZERO;

    public void incrementQuantity() {
        this.quantity = this.quantity + 1;
    }

    public void decrementQuantity() {
        this.quantity = this.quantity - 1;
    }

    public boolean isEmptyCartItem() {
        return 0 == quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;

        return Objects.equals(id, cartItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
