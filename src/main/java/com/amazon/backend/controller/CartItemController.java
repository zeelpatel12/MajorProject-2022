package com.amazon.backend.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.backend.model.CartItem;
import com.amazon.backend.service.CartItemService;



@RestController
/* @CrossOrigin */
@RequestMapping("/api")
public class CartItemController {
	 @Autowired 
	    private CartItemService cartItemService ; 
	    
	    @GetMapping("/cart-items")
	    public ResponseEntity<List<CartItem>> getCartItems () {
	        return ResponseEntity.ok(cartItemService.getCartItems());
	    }

	    @GetMapping("/cart-items/{id}/{productId}")
	    public ResponseEntity<CartItem> getCartItem (@PathVariable("id") Long id,
	                                                 @PathVariable("productId") Long productId) {
	        return ResponseEntity.ok(cartItemService.getCartItem(id, productId));
	    }

}
