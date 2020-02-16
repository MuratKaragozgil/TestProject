package com.shopping.cart.controller;

import com.shopping.cart.exception.CommonError;
import com.shopping.cart.exception.GenericException;
import com.shopping.cart.model.dto.CategoryDTO;
import com.shopping.cart.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Murat Karagözgil
 */
@Controller
@RequestMapping(value = "category")
public class CategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("get/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategoryById(HttpServletRequest request, @PathVariable("categoryId") Long categoryId) {
        logUserAction(request, "GetCategoryById::{}", categoryId);

        CategoryDTO categoryDTO = categoryService.getCategoryById(categoryId);

        if (categoryDTO == null) {
            throw new GenericException(CommonError.CATEGORY_NOT_FOUND);
        }

        return ResponseEntity.ok(categoryDTO);
    }

    @GetMapping("get-all")
    public ResponseEntity<List<CategoryDTO>> getAllCategories(HttpServletRequest request) {
        logUserAction(request, "GetAllCategories");

        List<CategoryDTO> categoryDTOList = categoryService.getAllCategories();

        return ResponseEntity.ok(categoryDTOList);
    }

    @PostMapping("save")
    public ResponseEntity<CategoryDTO> saveCategory(HttpServletRequest request, @RequestBody CategoryDTO categoryDTO) {
        logUserAction(request, "SaveCategoryDto::{}", categoryDTO);

        CategoryDTO savedCategory = categoryService.saveCategory(categoryDTO);

        return ResponseEntity.ok(savedCategory);
    }
}
