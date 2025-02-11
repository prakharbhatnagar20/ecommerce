package com.ecom.ecommerce.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.ecommerce.exception.OrderException;
import com.ecom.ecommerce.exception.UserException;
import com.ecom.ecommerce.model.Address;
import com.ecom.ecommerce.model.Order;
import com.ecom.ecommerce.model.User;
import com.ecom.ecommerce.service.OrderService;
import com.ecom.ecommerce.service.UserService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<Order> createOrder(@RequestBody Address address, @RequestHeader("Authorization") String token) throws UserException {
       
        User user = userService.findUserByJwt(token);
        Order order = orderService.createOrder(user, address);

        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Order>> userOrderHistory(@RequestHeader("Authorization") String token) throws UserException {
        User user = userService.findUserByJwt(token);
        List<Order> orders = orderService.userOrderHistory(user.getId());
        return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@RequestHeader("Authorization") String token, Long orderId) throws UserException, OrderException {
        User user = userService.findUserByJwt(token);
        Order order = orderService.findOrderById(orderId);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }
    
}
