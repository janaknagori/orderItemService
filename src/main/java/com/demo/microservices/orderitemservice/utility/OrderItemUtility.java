package com.demo.microservices.orderitemservice.utility;

import com.demo.microservices.orderitemservice.constant.AppConstant;
import com.demo.microservices.orderitemservice.response.OrderItemResponse;

public class OrderItemUtility {
	
	public static OrderItemResponse getServiceResponse(final Object res, final String errorMessage) {
		if (null != res) {
			return getSuccessServiceResponse(res);
		} else {
			return getFailureServiceResponse(errorMessage);
		}
	}

	public static OrderItemResponse getServiceResponse(final Object res) {
		return getServiceResponse(res, AppConstant.NO_RECORD_FOUND);
	}

	public static OrderItemResponse getSuccessServiceResponse(final Object result) {
		OrderItemResponse response = new OrderItemResponse();
		response.setStatus(AppConstant.SUCCESS);
		response.setResponseMessage(AppConstant.SUCCESS);
		response.setResObject(result);
		return response;
	}

	/**
	 * get Failure Service Response with message
	 * 
	 * @param message
	 * @return ServiceResponse
	 */
	public static OrderItemResponse getFailureServiceResponse(final String message) {
		OrderItemResponse response = new OrderItemResponse();
		response.setStatus(AppConstant.FAILED);
		response.setResponseMessage(message);
		return response;
	}

	/**
	 * get Failure Service Response with message and object
	 * 
	 * @param message
	 * @param result
	 * @return ServiceResponse
	 */
	public static OrderItemResponse getFailureServiceResponse(final String message, final Object result) {
		OrderItemResponse response = new OrderItemResponse();
		response.setStatus(AppConstant.FAILED);
		response.setResponseMessage(message);
		response.setResObject(result);
		return response;
	}
	
	/**
	 * Validate double value
	 * 
	 * @param value
	 * @return boolean
	 */
	public static boolean hasPrice(Double value) {
		if (value != null && value.doubleValue() > 0)
			return true;
		return false;
	}
	
	/**
	 * Validate integer value
	 * 
	 * @param value
	 * @return boolean
	 */
	public static boolean hasQty(Integer value) {
		if (value != null && value.intValue() > 0)
			return true;
		return false;
	}
	
	/**
	 * Validate string size
	 * 
	 * @param value
	 * @return boolean
	 */
	public static boolean strSize(String value) {
		if (value != null && value.length() > 4)
			return true;
		return false;
	}
	
}
