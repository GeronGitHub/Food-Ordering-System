package com.qa.foodordering.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "customer_details")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private int id;
	
	@Column(name = "customer_name")
	@NotNull
	@Pattern(regexp = "^[A-Za-z]*", message = "Invalid name - must only contain alphabetical characters !!")
	@Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters long !!")
	private String name;
	
	@Column(name = "customer_street")
	@NotNull
	@Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Invalid street - must only contain alphanumeric characters !!")
	@Size(min = 2, max = 20, message = "Street must be between 2 and 20 characters long !!")
	private String street;
	
	@Column(name = "customer_postcode")
	@NotNull
	@Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Invalid postcode - must only contain alphanumeric characters !!")
	@Size(min = 6, max = 10, message = "Street must be between 6 and 10 characters long !!")
	private String postcode;
	
	@Column(name = "customer_password")
	@NotNull
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Invalid password - must only contain alphanumeric characters !!")
	@Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters long !!")
	private String password;
	
	
}
