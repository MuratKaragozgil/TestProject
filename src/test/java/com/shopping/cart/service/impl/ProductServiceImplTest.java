package com.shopping.cart.service.impl;

import com.shopping.cart.mapper.ProductMapper;
import com.shopping.cart.model.Category;
import com.shopping.cart.model.Product;
import com.shopping.cart.model.dto.ProductDTO;
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

/**
 * @author Murat Karagözgil
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    private Product product;
    private ProductDTO productDTO;
    private Category category;

    @Before
    public void setup() {
        category = Category.builder() //
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
    public void shouldFindProductById() {
        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(product));
        Mockito.when(productMapper.convertToDTO(Mockito.any())).thenReturn(productDTO);

        productService.getProductById(2L);
    }

    @Test
    public void shouldSave() {
        Mockito.when(productMapper.convertToEntity(Mockito.any())).thenReturn(product);
        Mockito.when(productRepository.save(Mockito.any())).thenReturn(product);
        Mockito.when(productMapper.convertToDTO(Mockito.any())).thenReturn(productDTO);

        productService.saveProduct(productDTO);
    }
}