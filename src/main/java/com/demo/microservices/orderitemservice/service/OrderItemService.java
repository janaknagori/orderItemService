package com.demo.microservices.orderitemservice.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.microservices.orderitemservice.model.OrderItem;

public interface OrderItemService extends JpaRepository<OrderItem, Long>{
	/**
	 * Find itmes by id and status
	 * 
	 * @param id
	 * @param status
	 * @return OrderItem
	 */
	OrderItem findByItemIdAndStatus(Long id, boolean status);
	
}
