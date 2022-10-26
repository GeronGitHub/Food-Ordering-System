package com.qa.foodordering.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.foodordering.entity.Customer;
import com.qa.foodordering.exception.CustomerAlreadyExistsException;
import com.qa.foodordering.exception.CustomerNotFoundException;

@Service
public interface CustomerService {

	public List<Customer> getAllCustomers();
	public Customer getCustomerByID(int id) throws CustomerNotFoundException;
	public List<Customer> getCustomerByName(String name);
	public List<Customer> getCustomerByAddress(String address);
	public List<Customer> getCustomerByPostCode(String postcode);
	public List<Customer> getCustomerByAddressAndPostcode(String address, String postcode);
	public Customer addCustomer(Customer customer) throws CustomerAlreadyExistsException;
	public Customer updateCustomerDetails(int id, String name, String address, String postcode, String password) throws CustomerNotFoundException;
	public Customer updateAddressAndPostCode(int id, String address, String postcode) throws CustomerNotFoundException;
	public boolean deleteCustomer(int id) throws CustomerNotFoundException;
	
}
