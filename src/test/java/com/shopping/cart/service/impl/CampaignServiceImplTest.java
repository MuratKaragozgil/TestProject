package com.shopping.cart.service.impl;

import com.shopping.cart.mapper.CampaignMapper;
import com.shopping.cart.model.Campaign;
import com.shopping.cart.model.Category;
import com.shopping.cart.model.DiscountType;
import com.shopping.cart.model.dto.CampaignDTO;
import com.shopping.cart.repository.CampaignRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * @author Murat Karagözgil
 */
@RunWith(MockitoJUnitRunner.class)
public class CampaignServiceImplTest {

    @InjectMocks
    private CampaignServiceImpl campaignService;

    @Mock
    private CampaignRepository campaignRepository;

    @Mock
    private CampaignMapper campaignMapper;

    private Campaign campaign;
    private CampaignDTO campaignDTO;
    private Category category;

    @Before
    public void setup() {
        category = Category.builder() //
                .id(1L) //
                .title("category") //
                .build();

        campaign = Campaign.builder()
                .id(2L) //
                .title("campaign") //
                .category(category) //
                .itemLimit(3) //
                .discountAmount(new BigDecimal(500)) //
                .discountType(DiscountType.RATE) //
                .build();
        campaignDTO = CampaignDTO.builder()
                .id(2L) //
                .title("campaign") //
                .categoryId(category.getId()) //
                .itemLimit(3) //
                .discountAmount(new BigDecimal(500)) //
                .discountType(DiscountType.RATE) //
                .build();
    }

    @Test
    public void shouldFindCampaignById() {
        Mockito.when(campaignRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(campaign));
        Mockito.when(campaignMapper.convertToDTO(Mockito.any())).thenReturn(campaignDTO);

        campaignService.getCampaignById(2L);
    }

    @Test
    public void shouldSave() {
        Mockito.when(campaignMapper.convertToDTO(Mockito.any())).thenReturn(campaignDTO);
        Mockito.when(campaignRepository.save(Mockito.any())).thenReturn(campaign);
        Mockito.when(campaignMapper.convertToDTO(Mockito.any())).thenReturn(campaignDTO);

        campaignService.saveCampaign(campaignDTO);
    }
}