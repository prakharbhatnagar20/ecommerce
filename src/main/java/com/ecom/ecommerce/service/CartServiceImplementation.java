package com.ecom.ecommerce.service;

import org.springframework.stereotype.Service;

import com.ecom.ecommerce.exception.ProductException;
import com.ecom.ecommerce.exception.UserException;
import com.ecom.ecommerce.model.Cart;
import com.ecom.ecommerce.model.CartItem;
import com.ecom.ecommerce.model.Product;
import com.ecom.ecommerce.model.User;
import com.ecom.ecommerce.repository.CartRepository;
import com.ecom.ecommerce.request.AddItemRequest;

@Service
public class CartServiceImplementation implements CartService {

    private CartRepository cartRepository;
    private CartItemService cartItemService;
    private ProductService productService;

    public CartServiceImplementation(CartRepository cartRepository, CartItemService cartItemService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.cartItemService = cartItemService;
        this.productService = productService;
    }

    @Override
    public Cart createCart(User user) {
        // TODO Auto-generated method stub
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest addItemRequest) throws ProductException {
        // TODO Auto-generated method stub
        Cart cart = cartRepository.findByUserId(userId);
        Product product = productService.findProductById(addItemRequest.getProductid());
        CartItem isPresent = cartItemService.isCartItemExist(cart, product, addItemRequest.getSize(), userId);
        if(isPresent==null){
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(addItemRequest.getQuantity());
            cartItem.setUserId(userId);

            int price = addItemRequest.getQuantity() * product.getDiscountedPrice();
            cartItem.setPrice(price);
            cartItem.setSize(addItemRequest.getSize());

            CartItem createdCartItem = cartItemService.createCartItem(cartItem);
            cart.getCartItems().add(createdCartItem);
        }
        return "Item added to cart";
    }

    @Override
    public Cart findUserCart(Long userId) throws UserException {
        // TODO Auto-generated method stub
        Cart cart = cartRepository.findByUserId(userId);
        int totalPrice = 0;
        int totalDiscountedPrice = 0;
        int totalItem = 0;
        for(CartItem cartItem: cart.getCartItems()){
            totalPrice += cartItem.getPrice();
            totalDiscountedPrice += cartItem.getDiscountedPrice();
            totalItem += cartItem.getQuantity();
        }
        cart.setTotalPrice(totalPrice);
        cart.setTotalDiscountedPrice(totalDiscountedPrice);
        cart.setTotalItem(totalItem);
        cart.setDiscount(totalPrice-totalDiscountedPrice);
        return cartRepository.save(cart);
    }
    
}
