package com.qa.foodordering.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.foodordering.dto.CustomerDto;
import com.qa.foodordering.entity.Customer;
import com.qa.foodordering.entity.Order;
import com.qa.foodordering.exception.CustomerAlreadyExistsException;
import com.qa.foodordering.exception.CustomerNotFoundException;
import com.qa.foodordering.exception.PassNotCorrectException;
import com.qa.foodordering.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
    ModelMapper modelMapper;
	
	
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
	public List<Customer> getCustomerByStreet(String street) {
		
		return this.customerRepository.findByStreet(street);
	}

	@Override
	public List<Customer> getCustomerByPostCode(String postcode) {
		
		return this.customerRepository.findByPostcode(postcode);
	}
	
	@Override
	public List<Customer> getCustomerByStreetAndPostcode(String street, String postcode) {
		
		return this.customerRepository.findByStreetAndPostcode(street, postcode);
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
	public Customer updateCustomerDetails(int id, String name, String street, String postcode, String password) throws CustomerNotFoundException{
		
		Customer updatedCustomer = null;
		
		Optional<Customer> optionalCustomerFoundByID = this.customerRepository.findById(id);
		
		if (!optionalCustomerFoundByID.isPresent()) {
			throw new CustomerNotFoundException();
		}
		else {
			int rows = this.customerRepository.updateCustomerDetails(id, name, street, postcode, password);
			if (rows > 0) {
				updatedCustomer = this.customerRepository.findById(id).get();
			}
		}
		
		return updatedCustomer;
	}

	@Override
	public Customer updateStreetAndPostCode(int id, String street, String postcode) throws CustomerNotFoundException {
		
		Customer updatedCustomer = null;
		
		Optional<Customer> optionalCustomerFoundByID = this.customerRepository.findById(id);
		
		if (!optionalCustomerFoundByID.isPresent()) {
			throw new CustomerNotFoundException();
		}
		else {
			int rows = this.customerRepository.updateCustomerStreetAndPostCode(id, street, postcode);
			if (rows > 0) {
				updatedCustomer = this.customerRepository.findById(id).get();
			}
		}
		
		return updatedCustomer;
	}
	
	/* TO UPDATE ORDER ON CUSTOMER OBJECT
	 * @Override public Customer updateOrder(int id, List<Order> orderList) throws
	 * CustomerNotFoundException { Customer updatedCustomer = null;
	 * 
	 * Optional<Customer> optionalCustomerFoundByID =
	 * this.customerRepository.findById(id); return updatedCustomer; }
	 */

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

	@Override 
	public CustomerDto login(int id,String password) throws CustomerNotFoundException, PassNotCorrectException {
		Customer customer;
		Optional<Customer> customerFoundByIdOptional = this.customerRepository.findById(id);
 
		if (!customerFoundByIdOptional.isPresent()) {
			throw new CustomerNotFoundException();
		}else {
			customer = customerFoundByIdOptional.get();
			if(customer.getId() == id && customer.getPassword().equals(password)) {
				System.out.println("successful Login!");
			}else {
				throw new PassNotCorrectException();
			}
		}
		return mapToCustomerDto(customer);
 
	}

	@Override
	public List<CustomerDto> getAllCustomerDtos() {
		
		List<Customer> customerList = this.customerRepository.findAll();
		List<CustomerDto> customerDtoList = new ArrayList<>();
		
		customerList.forEach(cust -> {
			CustomerDto customerDto = CustomerDto.builder().id(cust.getId())
					.name(cust.getName())
					.street(cust.getStreet())
					.postcode(cust.getPostcode())
					.build();
			customerDtoList.add(customerDto);
		});
		
		return customerDtoList;
	}
	
	private CustomerDto mapToCustomerDto(Customer customer) {
	    return this.modelMapper.map(customer, CustomerDto.class);
	}

	
	

}
