package com.shopping.cart.mapper;

import com.shopping.cart.model.Category;
import com.shopping.cart.model.Product;
import com.shopping.cart.model.dto.ProductDTO;
import com.shopping.cart.repository.CategoryRepository;
import com.shopping.cart.repository.ProductRepository;
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
public class ProductMapperTest {
    @InjectMocks
    private ProductMapper productMapper;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    private Product product;
    private ProductDTO productDTO;
    private Category category;

    @Before
    public void setup() {
        category = Category.builder()
                .id(1L) //
                .title("category") //
                .build();

        product = Product.builder()
                .id(2L) //
                .title("product") //
                .category(category) // 
                .price(new BigDecimal(500)) //
                .build();

        productDTO = ProductDTO.builder()
                .id(2L) //
                .title("product") //
                .categoryId(category.getId()) //
                .price(new BigDecimal(500)) //
                .build();
    }

    @Test
    public void shouldToEntityReturnNull() {
        Product resultEntity = productMapper.convertToEntity(null);
        assertThat(resultEntity, equalTo(null));
    }

    @Test
    public void shouldToEntityHasId() {
        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(product));
        Mockito.when(categoryRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(category));

        Product resultEntity = productMapper.convertToEntity(productDTO);

        assertThat(resultEntity.getId(), equalTo(product.getId()));
        assertThat(resultEntity.getCategory(), equalTo(category));
        assertThat(resultEntity.getTitle(), equalTo(product.getTitle()));
        assertThat(resultEntity.getPrice(), equalTo(product.getPrice()));
    }

    @Test
    public void shouldToEntityNotFoundProduct() {
        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Mockito.when(categoryRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(category));

        productMapper.convertToEntity(productDTO);
    }

    @Test
    public void shouldToEntityNewProduct() {
        productDTO.setId(null);
        Mockito.when(categoryRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(category));

        Product resultEntity = productMapper.convertToEntity(productDTO);
        assertThat(resultEntity.getId(), equalTo(null));
    }

    @Test
    public void shouldToEntityNullCategory() {
        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(product));
        Mockito.when(categoryRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        Product resultEntity = productMapper.convertToEntity(productDTO);
        assertThat(resultEntity.getCategory(), equalTo(null));
    }

    @Test
    public void shouldToDtoReturnNull() {
        ProductDTO resultDTO = productMapper.convertToDTO(null);
        assertThat(resultDTO, equalTo(null));
    }

    @Test
    public void shouldToDto() {
        ProductDTO resultDTO = productMapper.convertToDTO(product);

        assertThat(resultDTO.getId(), equalTo(productDTO.getId()));
        assertThat(resultDTO.getCategoryId(), equalTo(productDTO.getCategoryId()));
        assertThat(resultDTO.getTitle(), equalTo(productDTO.getTitle()));
        assertThat(resultDTO.getPrice(), equalTo(productDTO.getPrice()));
    }
}