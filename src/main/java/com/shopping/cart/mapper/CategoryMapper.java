package com.shopping.cart.mapper;

import com.shopping.cart.model.Category;
import com.shopping.cart.model.dto.CategoryDTO;
import com.shopping.cart.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Murat Karagözgil
 */
@Component
public class CategoryMapper extends BaseMapper<Category, CategoryDTO> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category convertToEntity(CategoryDTO categoryDTO) {
        if (categoryDTO == null)
            return null;
        Category category = new Category();
        if (categoryDTO.getId() != null) {
            category = categoryRepository.findById(categoryDTO.getId()).orElse(null);
        }

        if (category == null) {
            category = new Category();
        }

        if (categoryDTO.getParentCategoryId() != null) {
            category.setParent(categoryRepository.findById(categoryDTO.getParentCategoryId()).orElse(null));
        }

        category.setTitle(categoryDTO.getTitle());

        return category;
    }

    @Override
    public CategoryDTO convertToDTO(Category category) {
        if (category == null) return null;

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId(category.getId());
        categoryDTO.setTitle(category.getTitle());
        categoryDTO.setParentCategoryId(category.getParent() != null ? category.getParent().getId() : null);

        return categoryDTO;
    }
}
