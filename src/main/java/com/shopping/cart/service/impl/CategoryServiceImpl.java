package com.shopping.cart.service.impl;

import com.shopping.cart.exception.CommonError;
import com.shopping.cart.exception.GenericException;
import com.shopping.cart.mapper.CategoryMapper;
import com.shopping.cart.model.Category;
import com.shopping.cart.model.dto.CategoryDTO;
import com.shopping.cart.repository.CategoryRepository;
import com.shopping.cart.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Murat Karagözgil
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(new GenericException(CommonError.CATEGORY_NOT_FOUND));
        return categoryMapper.convertToDTO(category);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(category -> categoryMapper.convertToDTO(category)).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDto) {
        return categoryMapper.convertToDTO(categoryRepository.save(categoryMapper.convertToEntity(categoryDto)));
    }
}
