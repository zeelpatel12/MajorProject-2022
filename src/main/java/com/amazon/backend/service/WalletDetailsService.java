package com.amazon.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazon.backend.model.OrderDetails;
import com.amazon.backend.model.WalletDetails;
import com.amazon.backend.repo.WalletDetailsRepository;

@Service
@Transactional
public class WalletDetailsService {

	@Autowired
	private WalletDetailsRepository repo;
	
	public WalletDetailsService() {
		
	}

	public List<WalletDetails> getAmount() {
		return (List<WalletDetails>) this.repo.findAll();}
	
	public WalletDetails getId(Long id) {
		try {
			return this.repo.findById(id).get();
		}catch(Exception e) {
			return null;
		}
	}
	 public WalletDetails addMoney(WalletDetails walletdetail) {
			this.repo.save(walletdetail);
			return walletdetail;
		}
	 
	 public void deductMoney(Long id) {
			
			try {
				this.repo.deleteById(id);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

}
