package com.shopping.cart.controller;

import com.shopping.cart.model.dto.ProductDTO;
import com.shopping.cart.service.ProductService;
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
@RequestMapping(value = "product")
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;

    @GetMapping("get/{productId}")
    public ResponseEntity<ProductDTO> getProductById(HttpServletRequest request, @PathVariable("productId") Long productId) {
        logUserAction(request, "GetProductById::{}", productId);

        ProductDTO productDTO = productService.getProductById(productId);

        return ResponseEntity.ok(productDTO);
    }

    @PostMapping("save")
    public ResponseEntity<ProductDTO> getAllProducts(HttpServletRequest request, @RequestBody ProductDTO productDTO) {
        logUserAction(request, "SaveProduct::{}", productDTO);

        ProductDTO savedProduct = productService.saveProduct(productDTO);

        return ResponseEntity.ok(savedProduct);
    }

    @GetMapping("get-all")
    public ResponseEntity<List<ProductDTO>> getAllProducts(HttpServletRequest request) {
        logUserAction(request, "GetAllProducts");

        List<ProductDTO> allProducts = productService.getAllProducts();

        return ResponseEntity.ok(allProducts);
    }
}