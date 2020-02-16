package com.shopping.cart.controller;

import com.shopping.cart.exception.CommonError;
import com.shopping.cart.exception.GenericException;
import com.shopping.cart.model.dto.CampaignDTO;
import com.shopping.cart.service.CampaignService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author Murat Karagözgil
 */
@RunWith(MockitoJUnitRunner.class)
public class CampaignControllerTest {

    @InjectMocks
    private CampaignController campaignController;

    @Mock
    private CampaignService campaignService;

    @Mock
    private MockHttpServletRequest request;

    private static final Long CAMPAIGN_ID = 1L;

    private static final CampaignDTO CAMPAIGN_DTO = CampaignDTO.builder()
            .id(CAMPAIGN_ID) //
            .categoryId(2L) //
            .title("Campaign") //
            .build();

    @Before
    public void setUp() {
        when(campaignService.getCampaignById(CAMPAIGN_ID)).thenReturn(CAMPAIGN_DTO);
        when(campaignService.saveCampaign(CAMPAIGN_DTO)).thenReturn(CAMPAIGN_DTO);
    }

    @Test
    public void shouldReturnCorrectCampaignWhenUsedCorrectParameters() {
        ResponseEntity<CampaignDTO> campaignDTOResponseEntity = campaignController.getCampaignById(request, CAMPAIGN_ID);
        assertThat(campaignDTOResponseEntity.getBody(), equalTo(CAMPAIGN_DTO));
    }

    @Test
    public void shouldReturnSavedCampaignWhenUsedCorrectParameters() {
        ResponseEntity<CampaignDTO> campaignDTOResponseEntity = campaignController.saveCampaign(request, CAMPAIGN_DTO);
        assertThat(campaignDTOResponseEntity.getBody(), equalTo(CAMPAIGN_DTO));
    }

}