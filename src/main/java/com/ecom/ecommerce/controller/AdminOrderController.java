package com.ecom.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.ecommerce.exception.OrderException;
import com.ecom.ecommerce.model.Order;
import com.ecom.ecommerce.response.ApiResponse;
import com.ecom.ecommerce.service.OrderService;

@RestController
@RequestMapping("/api/admin/order")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOdersHandler() {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
    }

    @PutMapping("/{orderId}/confirmed")
    public ResponseEntity<Order> ConfirmOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization") String token) throws OrderException{
        Order order = orderService.confirmedOrder(orderId);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
        
    }
    
    @PutMapping("/{orderId}/shipped")   
    public ResponseEntity<Order> shippedOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization") String token) throws OrderException{
        Order order = orderService.shippedOrder(orderId);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }
    @PutMapping("/{orderId}/delivered")
    public ResponseEntity<Order> deliveredOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization") String token) throws OrderException{
        Order order = orderService.deliveredOrder(orderId);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}/delete")
    public ResponseEntity<Order> deleteOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization") String token) throws OrderException{
        orderService.deleteOrder(orderId);
        ApiResponse res=new ApiResponse();
        res.setMessage("order deleted successfully");
        res.setStatus(true);
        return new ResponseEntity<Order>(HttpStatus.OK);
    }
}
