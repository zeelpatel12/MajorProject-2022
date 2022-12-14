package com.amazon.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.amazon.backend.model.CartItem;
import com.amazon.backend.model.CartItemPK;


public interface CartItemRepository extends JpaRepository <CartItem, CartItemPK> {
}
