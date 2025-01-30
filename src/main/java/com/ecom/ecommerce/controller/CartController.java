package com.ecom.ecommerce.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestHeader;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.ecom.ecommerce.exception.ProductException;
// import com.ecom.ecommerce.exception.UserException;
// import com.ecom.ecommerce.model.Cart;
// import com.ecom.ecommerce.model.User;
// import com.ecom.ecommerce.request.AddItemRequest;
// import com.ecom.ecommerce.response.ApiResponse;
// import com.ecom.ecommerce.service.CartService;
// import com.ecom.ecommerce.service.UserService;

// @RestController
// @RequestMapping("/api/cart")

// public class CartController {

//     @Autowired
//     private CartService cartService;

//     @Autowired
//     private UserService userService;

//     @GetMapping("/")
//     public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String token) throws UserException {
//         User user = userService.findUserByJwt(token);
//         Cart cart = cartService.findUserCart(user.getId());
//         return new ResponseEntity<Cart>(cart, HttpStatus.OK);
//     }

//     @GetMapping("/add")
//     public ResponseEntity<ApiResponse> addItemToCart(@RequestBody AddItemRequest req,@RequestHeader("Authorization") String token) throws UserException, ProductException {
//         User user = userService.findUserByJwt(token);
//         cartService.addCartItem(user.getId(), req);
//         ApiResponse res = new ApiResponse();
//         res.setMessage("Item added to cart successfully");
//         res.setStatus(true);
//         return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);
//     }

    
// }
