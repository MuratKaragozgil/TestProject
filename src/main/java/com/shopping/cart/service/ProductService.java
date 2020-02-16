package com.shopping.cart.service;

import com.shopping.cart.model.dto.ProductDTO;

import java.util.List;

/**
 * @author Murat Karagözgil
 */
public interface ProductService {

    ProductDTO getProductById(Long id);

    ProductDTO saveProduct(ProductDTO productDTO);

    List<ProductDTO> getAllProducts();
}
