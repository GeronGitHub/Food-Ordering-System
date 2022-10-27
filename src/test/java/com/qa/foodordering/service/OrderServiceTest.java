package com.qa.foodordering.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.qa.foodordering.entity.Order;
import com.qa.foodordering.exception.OrderAlreadyExistsException;
import com.qa.foodordering.repository.OrderRepository;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
	
	//CREATING THE MOCK OBJECT
	@Mock 
    private OrderRepository orderRepository;

    @Autowired
    @InjectMocks
    private OrderServiceImpl orderService;

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
	public void given_Order_To_Save_Return_Saved_Order() throws OrderAlreadyExistsException{
		when(orderRepository.findById(any())).thenReturn(Optional.empty());
		when(orderRepository.save(any())).thenReturn(o1);
		
		Order savedOrder = orderService.addOrder(o1);
		
		assertNotNull(savedOrder);
		assertEquals(1, savedOrder.getId());
        
    }
    
    @Test
	@DisplayName("save-order-test-throws-exception")
	public void given_Existing_Order_To_Save_Should_Throw_OrderAlreadyExistsException() throws OrderAlreadyExistsException{
		
		when(orderRepository.findById(any())).thenReturn(Optional.of(o1));
		
		assertThrows(OrderAlreadyExistsException.class, () -> orderService.addOrder(o1));

	} 
}
