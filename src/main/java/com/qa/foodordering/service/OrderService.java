package com.qa.foodordering.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.foodordering.entity.Order;
import com.qa.foodordering.exception.OrderAlreadyExistsException;
import com.qa.foodordering.exception.OrderNotFoundException;

@Service
public interface OrderService {
	
	public List<Order> getAllOrders();
	public Order getOrderByID(int id) throws OrderNotFoundException;
	public List<Order> getOrderByDate(String date);
	public List<Order> getOrderByCustomerID(int customerID);
	public List<Order> getOrderByStatus(String status);
	public List<Order> getOrderByDelivered(boolean delivered);
	public List<Order> getOrderByValue(double value);
	
	public Order addOrder(Order order) throws OrderAlreadyExistsException;
	public Order updateAllOrderDetails(int id, String date, int customerID, String status, boolean delivered, double value) throws OrderNotFoundException;
	public Order updateOrderStatusAndDelivery(int id, String status, boolean delivered) throws OrderNotFoundException;
	public boolean deleteOrder(int id) throws OrderNotFoundException;
}
