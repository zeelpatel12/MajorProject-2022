package com.amazon.backend.controller;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.backend.model.Category;
import com.amazon.backend.service.ApiResponse;
import com.amazon.backend.service.CategoryService;
import com.amazon.backend.service.Helper;
import com.amazon.backend.service.ProductService;



@RestController
/* @CrossOrigin */
@RequestMapping("/api")
public class CategoryController {
	@Autowired
    private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	
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
    
    
}