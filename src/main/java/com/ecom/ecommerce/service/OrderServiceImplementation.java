package com.ecom.ecommerce.service;

import java.util.List;

import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import com.ecom.ecommerce.exception.OrderException;
import com.ecom.ecommerce.model.Address;
import com.ecom.ecommerce.model.Order;
import com.ecom.ecommerce.model.User;
import com.ecom.ecommerce.repository.CartRepository;

@Service 
public class OrderServiceImplementation implements OrderService {

    private CartRepository cartRepository;
    private CartService cartItemService;
    private ProductService productService;

    OrderServiceImplementation(CartRepository cartRepository, CartService cartItemService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.cartItemService = cartItemService;
        this.productService = productService;
    }

    @Override
    public Order createOrder(User user, Address shippingAddress) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createOrder'");
    }

    @Override
    public Order findOrderById(Long orderId) throws OrderException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findOrderById'");
    }

    @Override
    public List<Order> userOrderHistory(Long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'userOrderHistory'");
    }

    @Override
    public Order placedOrder(Long orderId) throws OrderException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'placedOrder'");
    }

    @Override
    public Order confirmedOrder(Long orderId) throws OrderException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'confirmedOrder'");
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shippedOrder'");
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deliveredOrder'");
    }

    @Override
    public List<Order> getAllOrders() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllOrders'");
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteOrder'");
    }
    
}
