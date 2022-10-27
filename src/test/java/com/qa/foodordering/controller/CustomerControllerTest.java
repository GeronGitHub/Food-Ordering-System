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
import com.qa.foodordering.entity.Customer;
import com.qa.foodordering.service.CustomerServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {
	
	@Mock
	private CustomerServiceImpl customerService;
	
	@Autowired
	@InjectMocks
	private CustomerController customerController;
	
	@Autowired
	MockMvc mockMvc;
	
	Customer c1;
	Customer c2;
	Customer c3;
	
	List<Customer> customerList;
	
	@BeforeEach
	public void setUp() {
		
		//CREATING THE DATA NEEDED BEFORE EACH TEST CASE

		c1 = new Customer(1, "Geron", "Camberwell", "SE5 0EB", "geron12");
		c2 = new Customer(2, "Sho", "Peckham", "SE13 7QH", "shc12");
		c3 = new Customer(3, "May", "Brixton", "SE9 3TB", "may12");
		customerList = Arrays.asList(c1, c2, c3);
		
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
		
	}
	
	@AfterEach
	public void tearDown() {
		c1 = c2 = c3 = null;
		customerList = null;
	}
	
	@Test
	@DisplayName("save-customer-test")
	public void given_Customer_To_Save_Customer_Should_Return_Customer_As_JSON_With_Status_Create() throws Exception{
		when(customerService.addCustomer(any())).thenReturn(c1);
		mockMvc.perform(post("/api/v1/signup")
		        .contentType(MediaType.APPLICATION_JSON)
		        .content(asJsonString(c1)))
        		.andExpect(status().isCreated())
        		.andExpect(jsonPath("$.name").value("Geron"));
				
	}
	
	@Test
	@DisplayName("get-customers-test")
	public void given_AllCustomers_Should_Return_List() throws Exception {
		when(customerService.getAllCustomers()).thenReturn(customerList);
		mockMvc.perform(get("/api/v1/customers")
				        .accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[1].street").value("Peckham"));
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
