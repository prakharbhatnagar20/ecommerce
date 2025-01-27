package com.ecom.ecommerce.service;

import java.lang.StackWalker.Option;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecom.ecommerce.config.JwtProvider;
import com.ecom.ecommerce.exception.UserException;
import com.ecom.ecommerce.model.User;
import com.ecom.ecommerce.repository.UserRepository;

@Service
public class UserServiceImplemetation implements UserService {

    private UserRepository userRepository;
    private JwtProvider jwtProvider;

    public UserServiceImplemetation(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public User findUserById(Long userId) throws UserException {
        // TODO Auto-generated method stub

        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            return user.get();
        }
        throw new UserException("User not found");
    }

    @Override
    public User findUserByJwt(String jwt) throws UserException {
        // TODO Auto-generated method stub
        String email = jwtProvider.getEmailFromToken(jwt);
        User user = userRepository.findByEmail(email);
        if(user==null){
            throw new UserException("User not found");
        }

        return user;
    }
    
}
