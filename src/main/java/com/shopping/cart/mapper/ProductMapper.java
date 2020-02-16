package com.shopping.cart.mapper;

import com.shopping.cart.model.Product;
import com.shopping.cart.model.dto.ProductDTO;
import com.shopping.cart.repository.CategoryRepository;
import com.shopping.cart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Murat Karagözgil
 */
@Component
public class ProductMapper extends BaseMapper<Product, ProductDTO> {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product convertToEntity(ProductDTO productDTO) {
        if (productDTO == null) return null;

        Product product = null;

        if (productDTO.getId() != null) {
            product = productRepository.findById(productDTO.getId()).orElse(null);
        }

        if (product == null) {
            product = new Product();
        }

        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());

        if (productDTO.getCategoryId() != null) {
            product.setCategory(categoryRepository.findById(productDTO.getCategoryId()).orElse(null));
        }

        return product;
    }

    @Override
    public ProductDTO convertToDTO(Product product) {
        if (product == null) return null;

        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(product.getId());
        productDTO.setTitle(product.getTitle());
        productDTO.setPrice(product.getPrice());
        productDTO.setCategoryId(product.getCategory().getId());

        return productDTO;
    }
}
