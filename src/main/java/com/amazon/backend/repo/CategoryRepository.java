package com.amazon.backend.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.amazon.backend.model.*;


@Repository
@Transactional()
public interface CategoryRepository extends JpaRepository<Category,Long> {

	Category findByCategoryName(String categoryName);
}
