package com.shopping.cart.mapper;

import com.shopping.cart.exception.CommonError;
import com.shopping.cart.exception.GenericException;
import com.shopping.cart.model.Campaign;
import com.shopping.cart.model.dto.CampaignDTO;
import com.shopping.cart.repository.CampaignRepository;
import com.shopping.cart.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Murat Karagözgil
 */
@Component
public class CampaignMapper extends BaseMapper<Campaign, CampaignDTO> {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Campaign convertToEntity(CampaignDTO campaignDTO) {
        if (campaignDTO == null) return null;

        Campaign campaign = null;

        if (campaignDTO.getId() != null) {
            campaign = campaignRepository.findById(campaignDTO.getId()).orElseThrow(new GenericException(CommonError.CAMPAIGN_NOT_FOUND));
        }

        if (campaign == null) {
            campaign = new Campaign();
        }


        if (campaignDTO.getCategoryId() != null) {
            campaign.setCategory(categoryRepository.findById(campaignDTO.getCategoryId()).orElseThrow(new GenericException(CommonError.CATEGORY_NOT_FOUND)));
        }

        campaign.setTitle(campaignDTO.getTitle());
        campaign.setDiscountAmount(campaignDTO.getDiscountAmount());
        campaign.setItemLimit(campaignDTO.getItemLimit());
        campaign.setDiscountType(campaignDTO.getDiscountType());

        return campaign;
    }

    @Override
    public CampaignDTO convertToDTO(Campaign campaign) {
        if (campaign == null) return null;

        CampaignDTO campaignDTO = new CampaignDTO();

        campaignDTO.setId(campaign.getId());
        campaignDTO.setTitle(campaign.getTitle());
        campaignDTO.setCategoryId(campaign.getCategory().getId());
        campaignDTO.setDiscountAmount(campaign.getDiscountAmount());
        campaignDTO.setItemLimit(campaign.getItemLimit());
        campaignDTO.setDiscountType(campaign.getDiscountType());

        return campaignDTO;
    }
}
