package com.amazon.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amazon.backend.model.OrderDetails;

public interface OrderDetailRepository extends JpaRepository<OrderDetails,Long> {

}
