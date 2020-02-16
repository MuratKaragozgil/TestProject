package com.shopping.cart.service.impl;

import com.shopping.cart.mapper.CategoryMapper;
import com.shopping.cart.model.Category;
import com.shopping.cart.model.dto.CategoryDTO;
import com.shopping.cart.repository.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

/**
 * @author Murat Karagözgil
 */
@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    private Category category;
    private CategoryDTO categoryDTO;

    @Before
    public void setup() {
        category = Category.builder().id(1L).title("category").build();
        categoryDTO = CategoryDTO.builder().id(1L).title("category").build();
    }

    @Test
    public void shouldFindCategoryById() {
        Mockito.when(categoryRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(category));
        Mockito.when(categoryMapper.convertToDTO(Mockito.any())).thenReturn(categoryDTO);

        categoryService.getCategoryById(1L);
    }

    @Test
    public void shouldSave() {
        Mockito.when(categoryMapper.convertToEntity(Mockito.any())).thenReturn(category);
        Mockito.when(categoryRepository.save(Mockito.any())).thenReturn(category);
        Mockito.when(categoryMapper.convertToDTO(Mockito.any())).thenReturn(categoryDTO);

        categoryService.saveCategory(categoryDTO);
    }
}