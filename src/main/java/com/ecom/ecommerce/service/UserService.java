package com.ecom.ecommerce.service;

import com.ecom.ecommerce.exception.UserException;
import com.ecom.ecommerce.model.User;

public interface UserService {
    
    public User findUserById(Long userId) throws UserException;

    public User findUserByJwt(String jwt) throws UserException;
}
