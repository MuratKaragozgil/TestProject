package com.shopping.cart.controller;

import com.shopping.cart.model.dto.CategoryDTO;
import com.shopping.cart.service.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author Murat Karagözgil
 */
@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    @Mock
    private MockHttpServletRequest request;

    private static final CategoryDTO CATEGORY_DTO = CategoryDTO.builder().id(1L).title("Category").parentCategoryId(2L).build();
    private static final List<CategoryDTO> CATEGORY_DTO_LIST = Collections.singletonList(CATEGORY_DTO);

    @Before
    public void setUp() {
        when(categoryService.getCategoryById(1L)).thenReturn(CATEGORY_DTO);
        when(categoryService.saveCategory(CATEGORY_DTO)).thenReturn(CATEGORY_DTO);
        when(categoryService.getAllCategories()).thenReturn(CATEGORY_DTO_LIST);
    }

    @Test
    public void shouldReturnCategoryDTOWhenPassedCorrectParameters() {
        ResponseEntity<CategoryDTO> categoryDTOResponseEntity = categoryController.getCategoryById(request, CATEGORY_DTO.getId());
        assertThat(categoryDTOResponseEntity.getBody(), equalTo(CATEGORY_DTO));
    }

    @Test
    public void shouldReturnSavedCategoryWhenUsedCorrectParameters() {
        ResponseEntity<CategoryDTO> categoryDTOResponseEntity = categoryController.saveCategory(request, CATEGORY_DTO);
        assertThat(categoryDTOResponseEntity.getBody(), equalTo(CATEGORY_DTO));
    }

    @Test
    public void shouldReturnAllCategories() {
        ResponseEntity<List<CategoryDTO>> categoryDTOResponseEntity = categoryController.getAllCategories(request);
        assertThat(categoryDTOResponseEntity.getBody(), equalTo(CATEGORY_DTO_LIST));
    }
}