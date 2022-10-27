package com.qa.foodordering.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.foodordering.entity.Order;
import com.qa.foodordering.service.OrderServiceImpl;


@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {
	
	@Mock
	private OrderServiceImpl orderService;
	
	@Autowired
	@InjectMocks
	private OrderController orderController;
	
	@Autowired
	MockMvc mockMvc;
	
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
		
		mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
		
	}
	
	@AfterEach
	public void tearDown() {
		o1 = o2 = o3 = null;
		orderList = null;
	}
	
	@Test
	@DisplayName("save-order-test")
	public void given_Order_To_Save_Order_Should_Return_Order_As_JSON_With_Status_Create() throws Exception{
		when(orderService.addOrder(any())).thenReturn(o1);
		mockMvc.perform(post("/api/v1/orders")
		        .contentType(MediaType.APPLICATION_JSON)
		        .content(asJsonString(o1)))
        		.andExpect(status().isCreated())
        		.andExpect(jsonPath("$.date").value("10-07-2000"));
				
	}
	
	@Test
	@DisplayName("get-orders-test")
	public void given_AllOrders_Should_Return_List() throws Exception {
		when(orderService.getAllOrders()).thenReturn(orderList);
		mockMvc.perform(get("/api/v1/orders")
				        .accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[1].status").value("PROCESSING"));
	}
	
	public static String asJsonString(Object obj) {
		ObjectMapper Obj = new ObjectMapper();
		String jsonStr = null;
		
        try {

            jsonStr = Obj.writeValueAsString(obj);
            System.out.println(jsonStr);
        }
        catch (IOException e) {
        	System.out.println("SOME INTERNAL ERROR HAS OCCURED..");
            e.printStackTrace();
        }
        return jsonStr;
	}
}
