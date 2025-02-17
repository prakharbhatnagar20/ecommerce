package com.ecom.ecommerce.service;

import java.util.List;

import com.ecom.ecommerce.exception.ProductException;
import com.ecom.ecommerce.model.Review;
import com.ecom.ecommerce.model.User;
import com.ecom.ecommerce.request.ReviewRequest;

public interface ReviewService {
    
    public Review createReview(ReviewRequest req, User user) throws ProductException;
    public List<Review> getAllReview(Long productId);
}
