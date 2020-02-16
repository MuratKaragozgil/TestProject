package com.shopping.cart.repository;

import com.shopping.cart.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Murat Karagözgil
 */
@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
