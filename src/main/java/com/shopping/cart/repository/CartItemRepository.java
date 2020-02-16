package com.shopping.cart.repository;

import com.shopping.cart.model.CartItem;
import com.shopping.cart.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author Murat Karagözgil
 */
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query("select ci from CartItem ci where ci.product.category.id = :categoryId")
    Set<CartItem> findByCategoryId(@Param("categoryId") Long categoryId);

    @Query("select count(distinct ci.product.category) from CartItem ci where ci.shoppingCart = :shoppingCart")
    Integer findNumberOfDistinctCategory(@Param("shoppingCart") ShoppingCart shoppingCart);

}
