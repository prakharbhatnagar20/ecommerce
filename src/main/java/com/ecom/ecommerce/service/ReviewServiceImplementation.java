package com.ecom.ecommerce.service;

// import java.time.LocalDateTime;
// import java.util.List;

// import org.springframework.stereotype.Service;

// import com.ecom.ecommerce.exception.ProductException;
// import com.ecom.ecommerce.model.Product;
// import com.ecom.ecommerce.model.Review;
// import com.ecom.ecommerce.model.User;
// import com.ecom.ecommerce.repository.ProductRepository;
// import com.ecom.ecommerce.repository.ReviewRepository;
// import com.ecom.ecommerce.request.ReviewRequest;

// @Service
// public class ReviewServiceImplementation implements ReviewService {

//     private ReviewRepository reviewRepository;
//     private ProductService productService;
//     private ProductRepository productRepository;

//     public ReviewServiceImplementation(ReviewRepository reviewRepository, ProductService productService, ProductRepository productRepository) {
//         this.reviewRepository = reviewRepository;
//         this.productService = productService;
//         this.productRepository = productRepository;
//     }

//     @Override
//     public Review createReview(ReviewRequest req, User user) throws ProductException {
//         // TODO Auto-generated method stub
//         Product product = productService.findProductById(req.getProductId());
//         Review review = new Review();
//         review.setUser(user);
//         review.setProduct(product);
//         review.setReview(req.getReview());
//         review.setCreatedAt(LocalDateTime.now());

//         return reviewRepository.save(review);
//     }

//     @Override
//     public List<Review> getAllReview(Long productId) {
//         // TODO Auto-generated method stub
//         return reviewRepository.getAllProductsReview(productId);
//     }
     
// }
