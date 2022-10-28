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

import com.qa.foodordering.entity.Customer;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class CustomerRepositoryTest {
	
	@Autowired
	private CustomerRepository customerRepository;

	Customer c1;
	Customer c2;
	Customer c3;
	
	List<Customer> customerList;
	
	@BeforeEach
	public void setUp() {
		
		//CREATING THE DATA NEEDED BEFORE EACH TEST CASE
		
		
		c1 = new Customer(1, "Geron", "Camberwell", "SE5 0EB", "geron123");
		c2 = new Customer(2, "Sho", "Peckham", "SE13 7QH", "sho123");
		c3 = new Customer(3, "May", "Brixton", "SE9 3TB", "may123");
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
	public void given_Customer_To_Save_Return_Saved_Customer() {
		Customer savedCustomer = customerRepository.save(c1);
		assertNotNull(savedCustomer);
		assertEquals("Geron", savedCustomer.getName());
	}
	
	@Test
	@DisplayName("get-customer-with-non-existing-id-test")
	public void given_Customer_With_Non_Existing_ID_Return_Optional_Empty() {
		customerRepository.save(c1);
		assertThat(customerRepository.findById(321321).isEmpty());
	}
	
	@Test
	@DisplayName("get-customer-list-test")
	public void given_AllCustomer_Return_Customer_List() {
		
		//NEW SAVED OBJECT TAKES THE FIRST INDEX POSITION IN LIST
		customerRepository.save(c1); //INDEX POSITION 2
		customerRepository.save(c2); //INDEX POSITION 1
		customerRepository.save(c3); //INDEX POSITION 0
		
		List<Customer> customerList = customerRepository.findAll();
		System.out.println(customerList);
		assertEquals(3, customerList.size());
		
	}
	

}
