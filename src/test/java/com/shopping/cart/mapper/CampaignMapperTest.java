package com.shopping.cart.mapper;

import com.shopping.cart.exception.GenericException;
import com.shopping.cart.model.Campaign;
import com.shopping.cart.model.Category;
import com.shopping.cart.model.DiscountType;
import com.shopping.cart.model.dto.CampaignDTO;
import com.shopping.cart.repository.CampaignRepository;
import com.shopping.cart.repository.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Murat Karagözgil
 */
@RunWith(MockitoJUnitRunner.class)
public class CampaignMapperTest {

    @InjectMocks
    private CampaignMapper campaignMapper;

    @Mock
    private CampaignRepository campaignRepository;

    @Mock
    private CategoryRepository categoryRepository;

    private Campaign campaign;
    private CampaignDTO campaignDTO;
    private Category category;

    @Before
    public void setup() {
        category = Category.builder()
                .id(1L) //
                .title("category1") //
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
    public void shouldToEntityReturnNull() {
        Campaign resultEntity = campaignMapper.convertToEntity(null);
        assertThat(resultEntity, equalTo(null));
    }

    @Test
    public void shouldToEntityHasId() {
        Mockito.when(campaignRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(campaign));
        Mockito.when(categoryRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(category));

        Campaign resultEntity = campaignMapper.convertToEntity(campaignDTO);

        assertThat(resultEntity.getId(), equalTo(campaign.getId()));
        assertThat(resultEntity.getCategory(), equalTo(campaign.getCategory()));
        assertThat(resultEntity.getItemLimit(), equalTo(campaign.getItemLimit()));
        assertThat(resultEntity.getDiscountAmount(), equalTo(campaign.getDiscountAmount()));
        assertThat(resultEntity.getDiscountType(), equalTo(campaign.getDiscountType()));
    }

    @Test(expected = GenericException.class)
    public void shouldToEntityNotFoundCampaign() {
        Mockito.when(campaignRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(null));

        campaignMapper.convertToEntity(campaignDTO);
    }

    @Test
    public void shouldToEntityNewCampaign() {
        campaignDTO.setId(null);
        Mockito.when(categoryRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(category));

        Campaign resultEntity = campaignMapper.convertToEntity(campaignDTO);
        assertThat(resultEntity.getId(), equalTo(null));
    }

    @Test(expected = GenericException.class)
    public void shouldToEntityNullCategory() {
        Mockito.when(campaignRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(campaign));
        Mockito.when(categoryRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        campaignMapper.convertToEntity(campaignDTO);
    }

    @Test
    public void shouldToDtoReturnNull() {
        CampaignDTO resultDTO = campaignMapper.convertToDTO(null);
        assertThat(resultDTO, equalTo(null));
    }

    @Test
    public void shouldToDto() {
        CampaignDTO resultDTO = campaignMapper.convertToDTO(campaign);

        assertThat(resultDTO.getId(), equalTo(campaignDTO.getId()));
        assertThat(resultDTO.getCategoryId(), equalTo(campaignDTO.getCategoryId()));
        assertThat(resultDTO.getItemLimit(), equalTo(campaignDTO.getItemLimit()));
        assertThat(resultDTO.getDiscountAmount(), equalTo(campaignDTO.getDiscountAmount()));
        assertThat(resultDTO.getDiscountType(), equalTo(campaignDTO.getDiscountType()));
    }
}