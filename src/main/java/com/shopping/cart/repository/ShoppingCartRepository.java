package com.shopping.cart.repository;

import com.shopping.cart.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Murat Karagözgil
 */
@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}
