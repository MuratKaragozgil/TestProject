package com.shopping.cart.service.impl;

import com.shopping.cart.exception.CommonError;
import com.shopping.cart.exception.GenericException;
import com.shopping.cart.mapper.ProductMapper;
import com.shopping.cart.model.Product;
import com.shopping.cart.model.dto.ProductDTO;
import com.shopping.cart.repository.ProductRepository;
import com.shopping.cart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Murat Karagözgil
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(new GenericException(CommonError.PRODUCT_NOT_FOUND));
        return productMapper.convertToDTO(product);
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = productRepository.save(productMapper.convertToEntity(productDTO));
        return productMapper.convertToDTO(product);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(product -> productMapper.convertToDTO(product)).collect(Collectors.toList());
    }
}
