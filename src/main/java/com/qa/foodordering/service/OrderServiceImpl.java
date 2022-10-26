package com.qa.foodordering.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.qa.foodordering.entity.Order;
import com.qa.foodordering.exception.OrderAlreadyExistsException;
import com.qa.foodordering.exception.OrderNotFoundException;
import com.qa.foodordering.repository.OrderRepository;

public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public List<Order> getAllOrders() {

		return this.orderRepository.findAll();
	}

	@Override
	public Order getOrderByID(int id) throws OrderNotFoundException {

		Optional<Order> optionalOrderFoundByID = orderRepository.findById(id);
		
		if (!optionalOrderFoundByID.isPresent()) {
			throw new OrderNotFoundException();
		}
		
		return optionalOrderFoundByID.get();
	}

	@Override
	public List<Order> getOrderByDate(String date) {
		
		return this.orderRepository.findByDate(date);
	}

	@Override
	public List<Order> getOrderByCustomerID(int customerID) {
		
		return this.orderRepository.findByCustomerID(customerID);
	}

	@Override
	public List<Order> getOrderByStatus(String status) {
		
		return this.orderRepository.findByStatus(status);
	}

	@Override
	public List<Order> getOrderByDelivered(boolean delivered) {
		
		return this.orderRepository.findByDelivered(delivered);
	}

	@Override
	public List<Order> getOrderByValue(double value) {
		
		return this.orderRepository.findByValue(value);
	}

	@Override
	public Order addOrder(Order order) throws OrderAlreadyExistsException {
		
		Optional<Order> optionalOrderFoundByID = this.orderRepository.findById(order.getId());
		
		if (optionalOrderFoundByID.isPresent()) {
			throw new OrderAlreadyExistsException();
		}
		return this.orderRepository.save(order);
	}

	@Override
	public Order updateAllOrderDetails(int id, String date, int customerID, String status, boolean delivered,
			double value) throws OrderNotFoundException {
		
		Order updatedOrder = null;
		
		Optional<Order> optionalOrderFoundByID = this.orderRepository.findById(id);
		
		if (!optionalOrderFoundByID.isPresent()) {
			throw new OrderNotFoundException();
		}
		else {
			int rows = this.orderRepository.updateAllOrderDetails(id, date, customerID, status, delivered, value);
			if (rows > 0) {
				updatedOrder = this.orderRepository.findById(id).get();
			}
		}

		return updatedOrder;
	}

	@Override
	public Order updateOrderStatusAndDelivery(int id, String status, boolean delivered) throws OrderNotFoundException {
		Order updatedOrder = null;
		
		Optional<Order> optionalOrderFoundByID = this.orderRepository.findById(id);
		
		if (!optionalOrderFoundByID.isPresent()) {
			throw new OrderNotFoundException();
		}
		else {
			int rows = this.orderRepository.updateOrderStatusAndDelivery(id, status, delivered);
			if (rows > 0) {
				updatedOrder = this.orderRepository.findById(id).get();
			}
		}

		return updatedOrder;
	}

	@Override
	public boolean deleteOrder(int id) throws OrderNotFoundException {
		boolean status = false;
		
		Optional<Order> optionalOrderFoundByID = this.orderRepository.findById(id);
		
		if(!optionalOrderFoundByID.isPresent()) {
			throw new OrderNotFoundException();
		}
		else {
			this.orderRepository.delete(optionalOrderFoundByID.get());
			status = true;
		}
		
		return status;	
	}

}
