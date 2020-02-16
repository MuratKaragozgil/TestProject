package com.shopping.cart.service;

import com.shopping.cart.model.dto.CategoryDTO;

import java.util.List;

/**
 * @author Murat Karagözgil
 */
public interface CategoryService {

    CategoryDTO getCategoryById(Long id);

    List<CategoryDTO> getAllCategories();

    CategoryDTO saveCategory(CategoryDTO categoryDto);
}
