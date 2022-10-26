package com.qa.foodordering.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.foodordering.entity.Customer;
import com.qa.foodordering.exception.CustomerAlreadyExistsException;
import com.qa.foodordering.exception.CustomerNotFoundException;
import com.qa.foodordering.service.CustomerServiceImpl;

@RestController
@RequestMapping("api/v1")
public class CustomerController {
	
	@Autowired
	CustomerServiceImpl customerService;
	
	ResponseEntity<?> responseEntity;
	
	@GetMapping("/customers")
	public ResponseEntity<?> getAllCustomers(){
	
		try {
			List<Customer> customerList = this.customerService.getAllCustomers();
			responseEntity = new ResponseEntity<>(customerList, HttpStatus.OK);
		}
		catch (Exception e) {
			responseEntity = new ResponseEntity<>("INTERNAL ERROR HAS OCCURRED..", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return responseEntity;
	}
	
	@GetMapping("/customers/{id}")
	public ResponseEntity<?> getCustomerByID(@PathVariable("id") int id) throws CustomerNotFoundException{
		
		try {
			
			Customer customerByID = this.customerService.getCustomerByID(id);
			responseEntity = new ResponseEntity<>(customerByID, HttpStatus.OK);
		}
		catch (CustomerNotFoundException e) {
			throw e;
		}
		catch (Exception e) {
			
			System.out.println("INTERNAL ERROR HAS OCCURRED.. ");
			e.printStackTrace();
		}
		
		return responseEntity;
	}
	
	@GetMapping("/customers/street/{street}/postcode/{postcode}")
	public ResponseEntity<?> getCustomerByID(@PathVariable("street") String street, @PathVariable("postcode") String postcode){
		
		try {
			
			List<Customer> customerList = this.customerService.getCustomerByStreetAndPostcode(street, postcode);
			responseEntity = new ResponseEntity<>(customerList, HttpStatus.OK);
		}
		catch (Exception e) {
			
			System.out.println("INTERNAL ERROR HAS OCCURRED.. ");
			e.printStackTrace();
		}
		
		return responseEntity;
	}
	
	
	@PostMapping("/customers")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer) throws CustomerAlreadyExistsException{
		
		try {
			Customer addedCustomer = this.customerService.addCustomer(customer);
			responseEntity = new ResponseEntity<>(addedCustomer, HttpStatus.CREATED);
		}
		catch (CustomerAlreadyExistsException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>("INTERNAL ERROR HAS OCCURRED..", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}
	
	@PutMapping("/customers")
	public ResponseEntity<?> updateCustomerDetails(@RequestBody Customer customer) throws CustomerNotFoundException{
		
		try {
			Customer updatedCustomer = this.customerService.updateCustomerDetails(customer.getId(), customer.getName(), customer.getStreet(), customer.getPostcode(), customer.getUsername(), customer.getPassword());
			responseEntity = new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
		}
		catch (CustomerNotFoundException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>("INTERNAL ERROR HAS OCCURRED..", HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return responseEntity;
		
	}
	
	@PutMapping("/customer/update_address")
	public ResponseEntity<?> updateCustomerAddress(@RequestBody Customer customer) throws CustomerNotFoundException{
		
		try {
			Customer updatedCustomer = this.customerService.updateStreetAndPostCode(customer.getId(), customer.getStreet(), customer.getPostcode());
			responseEntity = new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
		}
		catch (CustomerNotFoundException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>("INTERNAL ERROR HAS OCCURRED..", HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return responseEntity;
	}
	
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable int id) throws CustomerNotFoundException{
		
		try {
			boolean status = this.customerService.deleteCustomer(id);
			responseEntity = new ResponseEntity<>(status, HttpStatus.OK);
		} catch (CustomerNotFoundException e) {
			throw e;
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>("INTERNAL ERROR OCCURRED..", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return responseEntity;
	}
}
