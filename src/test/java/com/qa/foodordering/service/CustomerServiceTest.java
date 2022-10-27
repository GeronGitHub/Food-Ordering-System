package com.qa.foodordering.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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

import com.qa.foodordering.entity.Customer;
import com.qa.foodordering.exception.CustomerAlreadyExistsException;
import com.qa.foodordering.repository.CustomerRepository;


@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
	
	@Mock //creates the mock object
	private CustomerRepository customerRepository;
	
	@Autowired
	@InjectMocks
	private CustomerServiceImpl customerService;
	
	Customer c1;
	Customer c2;
	Customer c3;
	
	List<Customer> customerList;
	
	@BeforeEach
	public void setUp() {
		
		//CREATING THE DATA NEEDED BEFORE EACH TEST CASE
		
		
		c1 = new Customer(1, "Geron", "Camberwell", "SE5 0EB", "geron12");
		c2 = new Customer(2, "Sho", "Peckham", "SE13 7QH", "sho12");
		c3 = new Customer(3, "May", "Brixton", "SE9 3TB", "may12");
		customerList = Arrays.asList(c1, c2, c3);
		
	}
	
	@AfterEach
	public void tearDown() {
		c1 = c2 = c3 = null;
		customerRepository.deleteAll();
		customerList = null;
	}
	
	@Test
	@DisplayName("save-customer-test")
	public void given_Customer_To_Save_Return_Saved_Customer() throws CustomerAlreadyExistsException{
		when(customerRepository.findById(any())).thenReturn(Optional.empty());
		when(customerRepository.save(any())).thenReturn(c1);
		
		Customer savedCustomer = customerService.addCustomer(c1);
		
		assertNotNull(savedCustomer);
		assertEquals(1, savedCustomer.getId());
		
	}
	
	@Test
	@DisplayName("save-customer-test-throws-exception")
	public void given_Existing_Customer_To_Save_Should_Throw_CustomerAlreadyExistsException() throws CustomerAlreadyExistsException{
		
		when(customerRepository.findById(any())).thenReturn(Optional.of(c1));
		
		assertThrows(CustomerAlreadyExistsException.class, () -> customerService.addCustomer(c1));

	} 
	
	
}
