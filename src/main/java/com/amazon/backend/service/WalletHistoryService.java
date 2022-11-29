package com.amazon.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazon.backend.model.WalletDetails;
import com.amazon.backend.model.WalletHistory;
import com.amazon.backend.repo.WalletHistoryRepo;

@Service
@Transactional
public class WalletHistoryService {

	@Autowired
	private WalletHistoryRepo repo;
	
	public WalletHistoryService() {
		
	}
	
	public List<WalletHistory> getId() {
		return (List<WalletHistory>) this.repo.findAll();}
	
	public WalletHistory getId(Long id) {
		try {
			return this.repo.findById(id).get();
		}catch(Exception e) {
			return null;
		}
	}
	
	
}
