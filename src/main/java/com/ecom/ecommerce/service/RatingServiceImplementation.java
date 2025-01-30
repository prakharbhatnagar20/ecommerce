package com.ecom.ecommerce.service;

// import java.time.LocalDateTime;
// import java.util.List;

// import org.springframework.cglib.core.Local;
// import org.springframework.stereotype.Service;

// import com.ecom.ecommerce.exception.ProductException;
// import com.ecom.ecommerce.model.Product;
// import com.ecom.ecommerce.model.Rating;
// import com.ecom.ecommerce.model.User;
// import com.ecom.ecommerce.repository.RatingRepository;
// import com.ecom.ecommerce.request.RatingRequest;

// @Service
// public class RatingServiceImplementation implements RatingService {

//     private RatingRepository ratingRepository;
//     private ProductService productService;

//     public RatingServiceImplementation(RatingRepository ratingRepository, ProductService productService) {
//         this.ratingRepository = ratingRepository;
//         this.productService = productService;
//     }
//     @Override
//     public Rating createRating(RatingRequest req, User user) throws ProductException {
//         // TODO Auto-generated method stub
//         Product product = productService.findProductById(req.getProductId());

//         Rating rating = new Rating();
//         rating.setProduct(product);
//         rating.setUser(user);
//         rating.setRating(req.getRating());
//         rating.setCreatedAt(LocalDateTime.now());
//         return ratingRepository.save(rating);
        
//     }

//     @Override
//     public List<Rating> getPrductRating(Long productId) {
//         // TODO Auto-generated method stub
//         return ratingRepository.getAllProductsRating(productId);
//     }
    
    
// }
