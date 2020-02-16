package com.shopping.cart.service;

import com.shopping.cart.model.dto.CampaignDTO;

/**
 * @author Murat Karagözgil
 */
public interface CampaignService {

    CampaignDTO getCampaignById(Long id);

    CampaignDTO saveCampaign(CampaignDTO campaignDTO);
}
