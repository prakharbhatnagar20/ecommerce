package com.ecom.ecommerce.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.ecommerce.exception.UserException;
import com.ecom.ecommerce.model.User;
import com.ecom.ecommerce.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/profile")
    public ResponseEntity<User> findUserByIdHandler(@RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.findUserByJwt(jwt);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    
}
