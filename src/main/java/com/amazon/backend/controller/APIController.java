package com.amazon.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.bind.annotation.*;

import com.amazon.backend.config.JwtUtil;
import com.amazon.backend.model.Category;
import com.amazon.backend.model.Product;
import com.amazon.backend.model.User;
import com.amazon.backend.model.cart.CartItem;
import com.amazon.backend.model.cart.CartItemPK;
import com.amazon.backend.service.CartItemService;
import com.amazon.backend.service.CategoryService;
import com.amazon.backend.service.JwtUserDetailsService;
import com.amazon.backend.service.ProductService;
import com.amazon.backend.service.UserService;

import com.amazon.backend.service.*;

import com.amazon.backend.service.ApiResponse;

import com.amazon.backend.dto.*;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class APIController {
    private final UserService userService;
    private final ProductService productService;
    private final CartItemService cartItemService;
    private final CategoryService categoryService;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    public APIController(CategoryService categoryService,UserService userService, ProductService productService, CartItemService cartItemService) {
        this.userService = userService;
        this.productService = productService;
        this.cartItemService = cartItemService;
        this.categoryService=categoryService;
    }

    @PostMapping("/create-token")
    public ResponseEntity<?> createToken (@RequestBody Map<String, String> user) throws Exception {
        Map<String, Object> tokenResponse = new HashMap<>();
        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(user.get("username"));
        final String token = jwtUtil.generateToken(userDetails);

        tokenResponse.put("token", token);
        return ResponseEntity.ok(tokenResponse);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers () {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser (@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }    

//    @GetMapping("/userRoleByID/{id}")
//    public ResponseEntity<String> getUserById(@PathVariable("id") Long id){
//    	return new ResponseEntity<String>(userService.getUser(id).getRole(),HttpStatus.OK);
//  
//    }
    
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

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> body = productService.listProducts();
        return new ResponseEntity<List<ProductDto>>(body, HttpStatus.OK);
    }

    @GetMapping("/products/{productID}")
    public ResponseEntity<ProductDto> ProductById(@PathVariable("productID") Long productID) {
    	 Optional<ProductDto> product1 = productService.getProduct(productID);
    	    ProductDto product = new ProductDto();
    	    product = product1.get();
    	    return new ResponseEntity<ProductDto>(product,HttpStatus.OK);

      }

    @GetMapping("/productsByCategoryId/{categoryId}")
    public ResponseEntity<List<ProductDto>> getProductsByKeyWord(@PathVariable("categoryId") Long categoryId) {
      List<ProductDto> body = productService.listProductsByCategoryId(categoryId);
      return new ResponseEntity<List<ProductDto>>(body, HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDto productDto) {
        Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
        if (!((Optional<?>) optionalCategory).isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT);
        }
        Category category = optionalCategory.get();
        productService.addProduct(productDto, category);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been added"), HttpStatus.CREATED);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productID") Long productID, @RequestBody ProductDto productDto) {
        Optional<Category> optionalCategory = categoryService.readCategory(Long.valueOf(productDto.getCategoryId()));
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Product is invalid"), HttpStatus.CONFLICT);
        }
        Category category = optionalCategory.get();
        productService.updateProduct(productID, productDto, category);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct1 (@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/allCats")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> body = categoryService.listCategories();
        return new ResponseEntity<List<Category>>(body, HttpStatus.OK);
    }

  @GetMapping("/categoryBy/{categoryID}")
  public ResponseEntity<Category> CategorybyId(@PathVariable("categoryID") Long categoryID){
    Optional<Category> category1 = categoryService.getCategoryById(categoryID);
    Category category = new Category();
    category = category1.get();
    return new ResponseEntity<Category>(category,HttpStatus.OK);

  }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody Category category) {
        if (Helper.notNull(categoryService.readCategory(category.getCategoryName()))) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category already exists"), HttpStatus.CONFLICT);
        }
        categoryService.createCategory(category);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "created the category"), HttpStatus.CREATED);
    }

    @PostMapping("/update/{categoryID}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryID") Long categoryID, @Valid @RequestBody Category category) {
        // Check to see if the category exists.
        if (Helper.notNull(categoryService.readCategory(categoryID))) {
            // If the category exists then update it.
            categoryService.updateCategory(categoryID, category);
            return new ResponseEntity<ApiResponse>(new ApiResponse(true, "updated the category"), HttpStatus.OK);
        }
        // If the category doesn't exist then return a response of unsuccessful.
        return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category does not exist"), HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/category/{categoryID}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryID") Long categoryID){
        categoryService.deleteByID(categoryID);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Category has been removed"), HttpStatus.OK);}
    
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
