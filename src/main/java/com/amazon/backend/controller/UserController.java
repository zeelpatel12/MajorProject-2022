package com.amazon.backend.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.backend.model.CartItem;
import com.amazon.backend.model.CartItemPK;
import com.amazon.backend.model.Product;
import com.amazon.backend.model.User;
import com.amazon.backend.service.CartItemService;
import com.amazon.backend.service.ProductService;
import com.amazon.backend.service.UserService;

@RestController
//@CrossOrigin
@RequestMapping("/api")
public class UserController {
	
    @Autowired
    private UserService userService;  
    @Autowired
    private ProductService productService;
    @Autowired 
    private CartItemService cartItemService ; 

	/*
	 * @PostMapping({"/registerNewUser"}) public User registerNewUser(@RequestBody
	 * User user) { return userService.registerNewUser(user); }
	 * 
	 * @GetMapping({"/forAdmin"})
	 * 
	 * @PreAuthorize("hasRole('Admin')") public String forAdmin(){ return
	 * "This URL is only accessible to the admin"; }
	 * 
	 * @GetMapping({"/forUser"})
	 * 
	 * @PreAuthorize("hasRole('User')") public String forUser(){ return
	 * "This URL is only accessible to the user"; }
	 */
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers () {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser (@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser (@PathVariable("id") Long id, @RequestBody Map<String, Object> user) {
        User newUser = new User(
                (String) user.get("username"),
                (String) user.get("password"),
                (String) user.get("email"),
                (String) user.get("name"),
                (String) user.get("address"),
                (String) user.get("phone"),
                (String) user.get("role")
        );

        return new ResponseEntity<>(userService.updateUser(id, newUser), HttpStatus.OK);
    }
    @GetMapping("/users/{id}/cart")
    public ResponseEntity<List<CartItem>> getUserCart (@PathVariable("id") Long id) {
        System.out.println(userService.getUser(id).getCartItems().size());
        return new ResponseEntity<>(userService.getUser(id).getCartItems(), HttpStatus.OK);
    }

    @PostMapping("/users/{id}/cart/add/{productId}")
    public ResponseEntity<User> addToUserCart (@PathVariable("id") Long id,
                                               @PathVariable("productId") Long productId) throws IOException {
        User user = userService.getUser(id);
        Product product = productService.getProductById(productId);

        CartItem cartItem = new CartItem(user, product, 1);
        cartItemService.addCartItem(cartItem);

        return new ResponseEntity<>(userService.getUser(id), HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}/cart/update/{productId}")
    public ResponseEntity<User> updateCartItem (@PathVariable("id") Long id,
                                                @PathVariable("productId") Long productId,
                                                @RequestBody CartItem cartItem) throws IOException {
        User user = userService.getUser(id);
        Product product = productService.getProductById(productId);

        cartItem.setPk(new CartItemPK(user, product));
        cartItemService.updateCartItem(cartItem);

        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}/cart/remove/{productId}")
    public ResponseEntity<User> removeFromUserCart (@PathVariable("id") Long id,
                                                    @PathVariable("productId") Long productId) {
        cartItemService.deleteCartItem(id, productId);

        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser (@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}