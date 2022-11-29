package com.amazon.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amazon.backend.model.TransactionDetails;

public interface TransactionDetailRepo extends JpaRepository <TransactionDetails,Long>{

}
