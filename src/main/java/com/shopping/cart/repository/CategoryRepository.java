package com.shopping.cart.repository;

import com.shopping.cart.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Murat Karagözgil
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
