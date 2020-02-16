package com.shopping.cart.repository;

import com.shopping.cart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Murat Karagözgil
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
