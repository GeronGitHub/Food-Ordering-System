package com.qa.foodordering.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.qa.foodordering.entity.Order;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class OrderRepositoryTest {
	
	
	@Autowired
	OrderRepository orderRepository;
	
	Order o1;
	Order o2;
	Order o3;
	
	List<Order> orderList;
	
	@BeforeEach
	public void setUp() {
		
		//CREATING THE DATA NEEDED BEFORE EACH TEST CASE

		o1 = new Order(1, "10-07-2000", "ACCEPTED", 50.00, true, 10, null);
		o2 = new Order(2, "11-08-2001", "PROCESSING", 40.00, false, 11, null);
		o3 = new Order(3, "12-09-2002", "DENIED", 30.00, false, 12, null);
		orderList = Arrays.asList(o1, o2, o3);
		
	}
	
	@AfterEach
	public void tearDown() {
		o1 = o2 = o3 = null;
		orderRepository.deleteAll();
		orderList = null;
	}
	
	@Test
	@DisplayName("save-order-test")
	public void given_Order_To_Save_Return_Saved_Order() {
		Order savedOrder = orderRepository.save(o1);
		assertNotNull(savedOrder);
		assertEquals("10-07-2000", savedOrder.getDate());
	}
	
	@Test
	@DisplayName("get-order-with-non-existing-id-test")
	public void given_Order_With_Non_Existing_ID_Return_Optional_Empty() {
		orderRepository.save(o1);
		assertThat(orderRepository.findById(321321).isEmpty());
	}
	
	@Test
	@DisplayName("get-order-list-test")
	public void given_AllOrder_Return_Order_List() {
		
		//NEW SAVED OBJECT TAKES THE FIRST INDEX POSITION IN LIST
		orderRepository.save(o1); //INDEX POSITION 2
		orderRepository.save(o2); //INDEX POSITION 1
		orderRepository.save(o3); //INDEX POSITION 0
		
		List<Order> orderList = orderRepository.findAll();
		assertEquals(3, orderList.size());
		assertEquals("ACCEPTED", orderList.get(2).getStatus());
		assertEquals(30.00, orderList.get(0).getValue());
	}
}
