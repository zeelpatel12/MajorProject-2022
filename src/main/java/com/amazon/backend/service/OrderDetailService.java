package com.amazon.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazon.backend.model.OrderDetails;
import com.amazon.backend.repo.OrderDetailRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderDetailService {

	@Autowired
	private OrderDetailRepository repo;

	public OrderDetailService() {
	}

	public List<OrderDetails> getAllOrders() {
		return this.repo.findAll();
	}
	public OrderDetails getId(Long id) {
		try {
			return this.repo.findById(id).get();
		} catch (Exception e) {
			return null;
		}
	}
 public OrderDetails addOrder(OrderDetails order) {
		this.repo.save(order);
		return order;
	}

	public OrderDetails createOrder(OrderDetails order) {
		return this.repo.save(order);
	}

	public void updateOrder(Long id, OrderDetails or) {
		try {
			repo.save(or);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void deleteOrder(Long id) {
		
		try {
			this.repo.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
