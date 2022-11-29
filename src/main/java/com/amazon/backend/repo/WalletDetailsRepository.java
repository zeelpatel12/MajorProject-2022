package com.amazon.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amazon.backend.model.WalletDetails;

public interface WalletDetailsRepository  extends JpaRepository <WalletDetails,Long>{

}
