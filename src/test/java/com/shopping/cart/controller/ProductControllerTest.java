package com.shopping.cart.controller;

import com.shopping.cart.model.dto.ProductDTO;
import com.shopping.cart.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author Murat Karagözgil
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Mock
    private MockHttpServletRequest request;

    private static final ProductDTO PRODUCT_DTO = ProductDTO.builder().id(1L).title("Product").categoryId(1L).price(new BigDecimal(100)).build();
    private static final List<ProductDTO> PRODUCT_DTO_LIST = Collections.singletonList(PRODUCT_DTO);

    @Before
    public void setUp() {
        when(productService.getProductById(1L)).thenReturn(PRODUCT_DTO);
        when(productService.saveProduct(PRODUCT_DTO)).thenReturn(PRODUCT_DTO);
        when(productService.getAllProducts()).thenReturn(PRODUCT_DTO_LIST);
    }

    @Test
    public void shouldReturnProductDTOWhenPassedCorrectParameters() {
        ResponseEntity<ProductDTO> productDTOResponseEntity = productController.getProductById(request, PRODUCT_DTO.getId());
        assertThat(productDTOResponseEntity.getBody(), equalTo(PRODUCT_DTO));
    }

    @Test
    public void shouldReturnSavedProductWhenUsedCorrectParameters() {
        ResponseEntity<ProductDTO> productDTOResponseEntity = productController.getAllProducts(request, PRODUCT_DTO);
        assertThat(productDTOResponseEntity.getBody(), equalTo(PRODUCT_DTO));
    }

    @Test
    public void shouldReturnAllCategories() {
        ResponseEntity<List<ProductDTO>> productDTOResponseEntity = productController.getAllProducts(request);
        assertThat(productDTOResponseEntity.getBody(), equalTo(PRODUCT_DTO_LIST));
    }
}