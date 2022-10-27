package com.qa.foodordering.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.foodordering.entity.Order;
import com.qa.foodordering.exception.OrderAlreadyExistsException;
import com.qa.foodordering.exception.OrderNotFoundException;
import com.qa.foodordering.service.OrderServiceImpl;

@RestController
@RequestMapping("api/v1")
public class OrderController {
	
	@Autowired
	OrderServiceImpl orderService;
	
	ResponseEntity<?> responseEntity;
	
	@GetMapping("/orders")
	public ResponseEntity<?> getAllOrders(){
		
		try {
			List<Order> orderList = this.orderService.getAllOrders();
			responseEntity = new ResponseEntity<>(orderList, HttpStatus.OK);
		}
		catch (Exception e) {
			responseEntity = new ResponseEntity<>("INTERNAL ERROR HAS OCCURRED..", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return responseEntity;
	}
	
	@GetMapping("/orders/{id}")
	public ResponseEntity<?> getOrderByID(@PathVariable("id") int id) throws OrderNotFoundException{
		
		try {
			
			Order orderByID = this.orderService.getOrderByID(id);
			responseEntity = new ResponseEntity<>(orderByID, HttpStatus.OK);
		}
		catch (OrderNotFoundException e) {
			throw e;
		}
		catch (Exception e) {
			
			System.out.println("INTERNAL ERROR HAS OCCURRED.. ");
			e.printStackTrace();
		}
		
		return responseEntity;
	}
	
	@PostMapping("/orders")
	public ResponseEntity<?> addOrder(@Valid @RequestBody Order order) throws OrderAlreadyExistsException{
		
		try {
			Order addedOrder = this.orderService.addOrder(order);
			responseEntity = new ResponseEntity<>(addedOrder, HttpStatus.CREATED);
		}
		catch (OrderAlreadyExistsException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>("INTERNAL ERROR HAS OCCURRED..", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}
	
	@PutMapping("/orders")
	public ResponseEntity<?> updateOrderDetails(@Valid @RequestBody Order order) throws OrderNotFoundException{
		
		try {
			Order updatedOrder = this.orderService.updateAllOrderDetails(order.getId(), order.getDate(), order.getCustomerID(), order.getStatus(), order.isDelivered(), order.getValue());
			responseEntity = new ResponseEntity<>(updatedOrder, HttpStatus.OK);
		}
		catch (OrderNotFoundException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>("INTERNAL ERROR HAS OCCURRED..", HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return responseEntity;
		
	}
	
	@PutMapping("/orders/update_status_and_delivery")
	public ResponseEntity<?> updateStatusAndDelivery(@Valid @RequestBody Order order) throws OrderNotFoundException{
		
		try {
			Order updatedOrder = this.orderService.updateOrderStatusAndDelivery(order.getId(), order.getStatus(), order.isDelivered());
			responseEntity = new ResponseEntity<>(updatedOrder, HttpStatus.OK);
		}
		catch (OrderNotFoundException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>("INTERNAL ERROR HAS OCCURRED..", HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return responseEntity;
		
	}
	
	@DeleteMapping("/orders/{id}")
	public ResponseEntity<?> deleteOrder(@PathVariable int id) throws OrderNotFoundException{
		
		try {
			boolean status = this.orderService.deleteOrder(id);
			responseEntity = new ResponseEntity<>(status, HttpStatus.OK);
		} catch (OrderNotFoundException e) {
			throw e;
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>("INTERNAL ERROR OCCURRED..", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		
		return responseEntity;
	}
}
