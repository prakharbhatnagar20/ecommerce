package com.ecom.ecommerce.service;

import java.util.List;

import com.ecom.ecommerce.exception.OrderException;
import com.ecom.ecommerce.model.Address;
import com.ecom.ecommerce.model.Order;
import com.ecom.ecommerce.model.User;

public interface OrderService {

    public Order createOrder(User user, Address shippingAddress);

    public Order findOrderById(Long orderId) throws OrderException;

    public List<Order> userOrderHistory(Long userId);

    public Order placedOrder(Long orderId) throws OrderException;

    public Order confirmedOrder(Long orderId) throws OrderException;

    public Order shippedOrder(Long orderId) throws OrderException;

    public Order deliveredOrder(Long orderId) throws OrderException;

    
    public List<Order> getAllOrders();

    public void deleteOrder(Long orderId) throws OrderException;

    
    
}
