package com.amazon.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.amazon.backend.model.Product;
import com.amazon.backend.model.User;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
@Repository
@Transactional()
public interface ProductRepository extends JpaRepository<Product, Long> {
//    void deleteById(Long id);
	 Optional<Product> findById (Long productID);
	
	@Query("select p from Product p where p.name like %:title%")
	  List<Product> searchByTitleLike(@Param("title") String title);
   
    @Query("select p from Product p where p.category.id = :categoryId")
    List<Product> findByCategoryId(@Param("categoryId") Long categoryId);
}
