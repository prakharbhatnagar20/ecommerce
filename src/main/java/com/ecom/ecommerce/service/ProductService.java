package com.ecom.ecommerce.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ecom.ecommerce.exception.ProductException;
import com.ecom.ecommerce.model.Product;
import com.ecom.ecommerce.request.CreateProductRequest;

public interface ProductService {

    public Product createProduct(CreateProductRequest createProductRequest);

    public String deleteProduct(Long productId) throws ProductException;

    public Product updateProduct(Long productId, Product product) throws ProductException;

    public Product findProductById(Long productId) throws ProductException;

    public List<Product> findProductByCategory(String category);

    public Page<Product> getAllProducts(String category, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice, Integer page, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize);
    
}
