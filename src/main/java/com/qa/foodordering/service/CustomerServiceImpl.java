package com.qa.foodordering.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.qa.foodordering.entity.Customer;
import com.qa.foodordering.exception.CustomerAlreadyExistsException;
import com.qa.foodordering.exception.CustomerNotFoundException;
import com.qa.foodordering.repository.CustomerRepository;

public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	
	@Override
	public List<Customer> getAllCustomers() {

		return this.customerRepository.findAll();
	}

	@Override
	public Customer getCustomerByID(int id) throws CustomerNotFoundException{
		
		Optional<Customer> optionalCustomerFoundByID = customerRepository.findById(id);
		if (!optionalCustomerFoundByID.isPresent()) {
			throw new CustomerNotFoundException();
		}
		return optionalCustomerFoundByID.get();
	}

	@Override
	public List<Customer> getCustomerByName(String name) {
		
		return this.customerRepository.findByName(name);
	}

	@Override
	public List<Customer> getCustomerByAddress(String address) {
		
		return this.customerRepository.findByAddress(address);
	}

	@Override
	public List<Customer> getCustomerByPostCode(String postcode) {
		
		return this.customerRepository.findByPostcode(postcode);
	}
	
	@Override
	public List<Customer> getCustomerByAddressAndPostcode(String address, String postcode) {
		
		return this.customerRepository.findByAddressAndPostcode(address, postcode);
	}

	@Override
	public Customer addCustomer(Customer customer) throws CustomerAlreadyExistsException{

		Optional<Customer> optionalCustomerFoundByID = this.customerRepository.findById(customer.getId());
		
		if (optionalCustomerFoundByID.isPresent()) {
			throw new CustomerAlreadyExistsException();
		}
		
		return this.customerRepository.save(customer);
	}

	@Override
	public Customer updateCustomerDetails(int id, String name, String address, String postcode, String password) throws CustomerNotFoundException{
		
		Customer updatedCustomer = null;
		
		Optional<Customer> optionalCustomerFoundByID = this.customerRepository.findById(id);
		
		if (!optionalCustomerFoundByID.isPresent()) {
			throw new CustomerNotFoundException();
		}
		else {
			int rows = this.customerRepository.updateCustomerDetails(id, name, address, postcode, password);
			if (rows > 0) {
				updatedCustomer = this.customerRepository.findById(id).get();
			}
		}
		
		return updatedCustomer;
	}

	@Override
	public Customer updateAddressAndPostCode(int id, String address, String postcode) throws CustomerNotFoundException {
		
		Customer updatedCustomer = null;
		
		Optional<Customer> optionalCustomerFoundByID = this.customerRepository.findById(id);
		
		if (!optionalCustomerFoundByID.isPresent()) {
			throw new CustomerNotFoundException();
		}
		else {
			int rows = this.customerRepository.updateCustomerAddressAndPostCode(id, address, postcode);
			if (rows > 0) {
				updatedCustomer = this.customerRepository.findById(id).get();
			}
		}
		
		return updatedCustomer;
	}

	@Override
	public boolean deleteCustomer(int id) throws CustomerNotFoundException {
		boolean status = false;
		
		Optional<Customer> optionalCustomerFoundByID = this.customerRepository.findById(id);
		
		if(!optionalCustomerFoundByID.isPresent()) {
			throw new CustomerNotFoundException();
		}
		else {
			this.customerRepository.delete(optionalCustomerFoundByID.get());
			status = true;
		}
		
		return status;	
	}

	

}
