package com.amazon.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amazon.backend.model.WalletHistory;

public interface WalletHistoryRepo extends JpaRepository <WalletHistory,Long> {

}
