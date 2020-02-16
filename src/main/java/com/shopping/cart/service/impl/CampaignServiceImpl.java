package com.shopping.cart.service.impl;

import com.shopping.cart.exception.CommonError;
import com.shopping.cart.exception.GenericException;
import com.shopping.cart.mapper.CampaignMapper;
import com.shopping.cart.model.Campaign;
import com.shopping.cart.model.dto.CampaignDTO;
import com.shopping.cart.repository.CampaignRepository;
import com.shopping.cart.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Murat Karagözgil
 */
@Service
public class CampaignServiceImpl implements CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private CampaignMapper campaignMapper;

    @Override
    public CampaignDTO getCampaignById(Long id) {
        Campaign campaign = campaignRepository.findById(id).orElseThrow(new GenericException(CommonError.CAMPAIGN_NOT_FOUND));
        return campaignMapper.convertToDTO(campaign);
    }

    @Override
    public CampaignDTO saveCampaign(CampaignDTO campaignDTO) {
        Campaign savedCampaign = campaignRepository.save(campaignMapper.convertToEntity(campaignDTO));
        return campaignMapper.convertToDTO(savedCampaign);
    }
}
