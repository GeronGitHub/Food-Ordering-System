package com.qa.foodordering.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.foodordering.dto.CustomerDto;
import com.qa.foodordering.entity.Customer;
import com.qa.foodordering.entity.Order;
import com.qa.foodordering.exception.CustomerAlreadyExistsException;
import com.qa.foodordering.exception.CustomerNotFoundException;
import com.qa.foodordering.exception.PassNotCorrectException;

@Service
public interface CustomerService {

	public List<Customer> getAllCustomers();
	public Customer getCustomerByID(int id) throws CustomerNotFoundException;
	public List<Customer> getCustomerByName(String name);
	public List<Customer> getCustomerByStreet(String street);
	public List<Customer> getCustomerByPostCode(String postcode);
	public List<Customer> getCustomerByStreetAndPostcode(String street, String postcode);
	public Customer addCustomer(Customer customer) throws CustomerAlreadyExistsException;
	public Customer updateCustomerDetails(int id, String name, String street, String postcode, String password) throws CustomerNotFoundException;
	public Customer updateStreetAndPostCode(int id, String street, String postcode) throws CustomerNotFoundException;
	//public Customer updateOrder(int id, List<Order> orderList) throws CustomerNotFoundException;
	public boolean deleteCustomer(int id) throws CustomerNotFoundException;
	
	public CustomerDto login(int id, String password) throws CustomerNotFoundException, PassNotCorrectException;
	
	public List<CustomerDto> getAllCustomerDtos();
	
}
