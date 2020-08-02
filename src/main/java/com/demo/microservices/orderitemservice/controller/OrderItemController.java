package com.demo.microservices.orderitemservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.microservices.orderitemservice.Exception.ItemNotFoundException;
import com.demo.microservices.orderitemservice.model.OrderItem;
import com.demo.microservices.orderitemservice.response.OrderItemResponse;
import com.demo.microservices.orderitemservice.service.OrderItemService;
import com.demo.microservices.orderitemservice.utility.OrderItemUtility;
import com.demo.microservices.orderitemservice.utility.Validation;

@RestController
public class OrderItemController {
	
	@Autowired
	private OrderItemService orderItemService;
	
	
	/**
	 * Will return orderItem
	 * 
	 * @param id
	 * @return OrderItemResponse
	 */
	@GetMapping(path="/getOrderItem")
	public OrderItem getOrderItem(@RequestParam Long id) {
		OrderItem item = null;
		item = orderItemService.findByItemIdAndStatus(id,true);
		if(item == null) {				
			throw new ItemNotFoundException("Order Item with id = "+id+" is not found");
		}
		
		return item;
	}
	
	
	/**
	 * Will save orderItem data
	 * 
	 * @param orderItem
	 * @return OrderItemResponse
	 */
	@PostMapping(path="saveOrderItem")
	@Validated
	public OrderItemResponse saveOrderItem(@Valid @RequestBody OrderItem orderItem) {
		OrderItem item = null;
		List<String> errorList = Validation.validateOrderItem(orderItem);
		if(CollectionUtils.isEmpty(errorList)) {
			try {
				orderItem.setStatus(true);
				item = orderItemService.save(orderItem);
			}catch (Exception e) {
				item = new OrderItem();
				e.printStackTrace();
			}
			return OrderItemUtility.getServiceResponse(item);
		}else {
			return OrderItemUtility.getServiceResponse(errorList);
		}
	}
	
	
	/**
	 * Will Update existing orderItem data
	 * 
	 * @param orderItem
	 * @return OrderItemResponse
	 */
	@PostMapping(path="updateOrderItem")
	public OrderItemResponse updateOrderItem(@RequestBody OrderItem orderItem) {
		OrderItem item = null;
		try {
			if(orderItem.getItemId() != null) {
				orderItem.setStatus(true);
				item = orderItemService.save(orderItem);
			}
		}catch (Exception e) {
			item = new OrderItem();
			e.printStackTrace();
		}
		return OrderItemUtility.getServiceResponse(item);
	}
	
	/**
	 * Will delete unused orderItem
	 * 
	 * @param id
	 * @return OrderItemResponse
	 */
	@DeleteMapping(path="deleteOrderItem")
	public OrderItemResponse deleteItem(@RequestParam Long id) {
		OrderItem item = null;
		try {
			item = orderItemService.findByItemIdAndStatus(id,true);
			if(item != null) {
				item.setStatus(false);
				orderItemService.save(item);
			}else {
				throw new ItemNotFoundException("id = "+id);				
			}
		}catch (Exception e) {
			item = new OrderItem();
			e.printStackTrace();
		}
		return OrderItemUtility.getServiceResponse(item);
	}

}
