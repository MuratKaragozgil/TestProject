package com.shopping.cart.repository;

import com.shopping.cart.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Murat Karagözgil
 */
@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
}
