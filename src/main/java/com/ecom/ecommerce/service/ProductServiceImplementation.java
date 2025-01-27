package com.ecom.ecommerce.service;

import java.time.LocalDateTime;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecom.ecommerce.exception.ProductException;
import com.ecom.ecommerce.model.Category;
import com.ecom.ecommerce.model.Product;
import com.ecom.ecommerce.repository.CategoryRepository;
import com.ecom.ecommerce.repository.ProductRepository;
import com.ecom.ecommerce.request.CreateProductRequest;

@Service
public class ProductServiceImplementation implements ProductService {

    private ProductRepository productRepository;
    private UserService userService;
    private CategoryRepository categoryRepository;

    ProductServiceImplementation(ProductRepository productRepository, UserService userService, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product createProduct(CreateProductRequest createProductRequest) {
        // TODO Auto-generated method stub

        Category topLevel = categoryRepository.findByName(createProductRequest.getTopLevelCategory());

        if(topLevel==null) {
            Category topLevelCategory = new Category();
            topLevelCategory.setName(createProductRequest.getTopLevelCategory());
            topLevelCategory.setLevel(1);
            topLevel = categoryRepository.save(topLevelCategory);
        }

        Category secondLevel = categoryRepository.findByNameAndParent(createProductRequest.getSecondLevelCategory(), topLevel.getName());
        if(secondLevel==null) {
            Category secondLevelCategory = new Category();
            secondLevelCategory.setName(createProductRequest.getSecondLevelCategory());
            secondLevelCategory.setLevel(2);
            secondLevelCategory.setParentCategory(topLevel);
            secondLevel = categoryRepository.save(secondLevelCategory);
        }

        Category thirdLevel = categoryRepository.findByNameAndParent(createProductRequest.getThirdLevelCategory(), secondLevel.getName());
        if(thirdLevel==null) {
            Category thirdLevelCategory = new Category();
            thirdLevelCategory.setName(createProductRequest.getThirdLevelCategory());
            thirdLevelCategory.setLevel(3);
            thirdLevelCategory.setParentCategory(secondLevel);
            thirdLevel = categoryRepository.save(thirdLevelCategory);
        }

        Product product = new Product();
        product.setTitle(createProductRequest.getTitle());
        product.setColor(createProductRequest.getColor());
        product.setDescription(createProductRequest.getDescription());
        product.setDiscountPersent(createProductRequest.getDiscountPersent());
        product.setDiscountedPrice(createProductRequest.getDiscountedPrice());
        product.setPrice(createProductRequest.getPrice());
        product.setQuantity(createProductRequest.getQuantity());
        product.setBrand(createProductRequest.getBrand());
        product.setSizes(createProductRequest.getSize());
        product.setPrice(createProductRequest.getPrice());
        product.setImageUrl(createProductRequest.getImageUrl());
        product.setCategory(thirdLevel);
        product.setCreatedAt(LocalDateTime.now());

        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    @Override
    public String deleteProduct(Long productId) throws ProductException {
        // TODO Auto-generated method stub
        Product product = findProductById(productId);
        product.getSizes().clear();
        productRepository.delete(product);
        return "Product deleted successfully";
    }

    @Override
    public Product updateProduct(Long productId, Product product) throws ProductException {
        // TODO Auto-generated method stub

        Product productToUpdate = findProductById(productId);

        if(productToUpdate.getQuantity()!=0){
            productToUpdate.setQuantity(product.getQuantity());
        }
        return productRepository.save(productToUpdate);
    }

    @Override
    public Product findProductById(Long productId) throws ProductException {
        // TODO Auto-generated method stub
        Optional<Product> product = productRepository.findById(productId);

        if(product.isPresent()) {
            return product.get();
        } else {
            throw new ProductException("Product not found with id"+productId);
        } 
    }

    @Override
    public List<Product> findProductByCategory(String category) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findProductByCategory'");
    }

    @Override
    public Page<Product> getAllProducts(String category, List<String> colors, List<String> sizes, Integer minPrice,
            Integer maxPrice, Integer page, Integer minDiscount, String sort, String stock, Integer pageNumber,
            Integer pageSize) {
        // TODO Auto-generated method stub
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        List<Product> products = productRepository.filterProducts(category, colors, sizes, minPrice, maxPrice, minDiscount, sort);
        if(!colors.isEmpty()){
            products = products.stream().filter(p->colors.stream().anyMatch(c->c.equalsIgnoreCase(p.getColor()))).collect(Collectors.toList());
        }
        if(stock!=null){
            if(stock.equals("in_stock")){
                products = products.stream().filter(p->p.getQuantity()>0).collect(Collectors.toList());
            }
            else if(stock.equals("out_of_stock")){
                products = products.stream().filter(p->p.getQuantity()<1).collect(Collectors.toList());
            }
        }
        int startIndex = (int)pageable.getOffset();
        int endIndex = Math.min(startIndex+pageable.getPageSize(), products.size());

        List<Product> pageContent = products.subList(startIndex, endIndex);

        Page<Product> filteredPage = new PageImpl<>(pageContent, pageable, products.size());
        return filteredPage;
    }
    
}
