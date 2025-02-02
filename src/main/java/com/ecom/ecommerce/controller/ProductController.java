package com.ecom.ecommerce.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.ecommerce.exception.ProductException;
import com.ecom.ecommerce.model.Product;
import com.ecom.ecommerce.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Page<Product>> findProductByCategoryHandler(@RequestParam String category, @RequestParam List<String> colors, @RequestParam List<String> sizes, @RequestParam Integer minPrice, @RequestParam Integer maxPrice, @RequestParam Integer minDiscount, @RequestParam String sort, @RequestParam String stock, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        Page<Product> products = productService.getAllProducts(category, colors, sizes, minPrice, maxPrice, minDiscount, sort, stock, pageNumber, pageSize);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/products/all")
    public ResponseEntity<List<Product>> findAllProductsHandler() {
        List<Product> products = productService.findAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("products//id/{productId}") 
    public ResponseEntity<Product> findProductByIdHandler(@RequestParam Long productId) throws ProductException {
        Product product = productService.findProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

   
    
}
