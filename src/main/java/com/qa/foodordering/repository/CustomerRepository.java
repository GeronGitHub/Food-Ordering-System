package com.qa.foodordering.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.foodordering.entity.Customer;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	//FIND CUSTOMER BY NAME
	List<Customer> findByName(String name);
	
	//FIND CUSTOMER BY POSTCODE
	List<Customer> findByPostcode(String postcode);
	
	//FIND CUSTOMER BY ADDRESS
	List<Customer> findByAddress(String address);
	
	//FIND CUSTOMER BY FULL ADDRES
	@Query("select c from Customer c where c.address = :address and c.postcode = :postcode")
	List<Customer> findByAddressAndPostcode(String address, String postcode);
	
	//UPDATING ALL OF THE CUSTOMERS DETAILS
	@Modifying
	@Query("update Customer c set c.name = :name, c.address = :address, c.postcode = :postcode, c.password = :password where c.id = :id")
	int updateCustomerDetails(int id, String name, String address, String postcode, String password);
	
	//UPDATING ONLY THE ADDRESS OF THE CUSTOMER
	@Modifying
	@Query("update Customer c set c.address = :address, c.postcode = :postcode where c.id = :id")
	int updateCustomerAddressAndPostCode(int id, String address, String postcode);

}

