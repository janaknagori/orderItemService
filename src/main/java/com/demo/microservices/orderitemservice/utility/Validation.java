package com.demo.microservices.orderitemservice.utility;

import java.util.ArrayList;
import java.util.List;

import com.demo.microservices.orderitemservice.model.OrderItem;

public class Validation {
	
	/**Validate OrderItem fields
	 *  
	 * @param orderItem
	 * @return List<String>
	 */
	public static List<String> validateOrderItem(OrderItem orderItem) {
		List<String> messageList = new ArrayList<String>();
		if(orderItem != null) {
			if(!OrderItemUtility.hasPrice(orderItem.getProductPrice())) {
				messageList.add("Invalid Price, It should be in decimal and greater than zero");
			}
			if(!OrderItemUtility.hasQty(orderItem.getQuantity())) {
				messageList.add("Invalid Quantity, It should be in digits and greater than zero");
			}
			if(!OrderItemUtility.strSize(orderItem.getProductName())) {
				messageList.add("Product Name should have atleast 4 characters");
			}
			if(!OrderItemUtility.strSize(orderItem.getProductCode())) {
				messageList.add("Product Code should have atleast 4 characters");
			}
		}
		
		return messageList;
	}

}
