package com.ecom.ecommerce.service;


import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecom.ecommerce.exception.CartItemException;
import com.ecom.ecommerce.exception.UserException;
import com.ecom.ecommerce.model.Cart;
import com.ecom.ecommerce.model.CartItem;
import com.ecom.ecommerce.model.Product;
import com.ecom.ecommerce.model.Size;
import com.ecom.ecommerce.model.User;
import com.ecom.ecommerce.repository.CartItemRepository;
import com.ecom.ecommerce.repository.CartRepository;

@Service
public class CartItemServiceImplementation implements CartItemService {


    private CartItemRepository cartItemRepository;

    private UserService userService;

    private CartRepository cartRepository;

    public CartItemServiceImplementation(CartItemRepository cartItemRepository, UserService userService, CartRepository cartRepository) {
        this.cartItemRepository = cartItemRepository;
        this.userService = userService;
        this.cartRepository = cartRepository;
    }

    @Override
    public CartItem createCartItem(CartItem cartItem) {
        // TODO Auto-generated method stub
        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice()*cartItem.getQuantity());
        CartItem createdCartItem = cartItemRepository.save(cartItem);
        return createdCartItem;
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
        // TODO Auto-generated method stub
       CartItem item = findCartItemById(id);
       User user = userService.findUserById(userId);
       if(user.getId().equals(userId)){
        item.setQuantity(cartItem.getQuantity());
        item.setPrice(item.getProduct().getPrice()*item.getQuantity());
        item.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice()*item.getQuantity());
       }
       return cartItemRepository.save(item);
    }

   

    @Override
    public void removeCartItem(Long userId, Long id) throws CartItemException, UserException {
        // TODO Auto-generated method stub
        CartItem cartItem = findCartItemById(id);
        User user = userService.findUserById(cartItem.getUserId());
        User reqUser = userService.findUserById(userId);
        if(user.getId().equals(reqUser.getId())){
            cartItemRepository.deleteById(id);
        }
        else{
            throw new CartItemException("User not authorized to delete this cart item");
        }

    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {
        // TODO Auto-generated method stub
        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
        if(cartItem.isPresent()){
            return cartItem.get();
        }
        throw new CartItemException("Cart item not found");
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId){
        // TODO Auto-generated method stub
        CartItem cartItem = cartItemRepository.isCartItemExist(cart, product, size, userId);
        return cartItem;
    }
    
}
