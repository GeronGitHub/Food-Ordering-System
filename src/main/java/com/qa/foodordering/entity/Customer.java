package com.qa.foodordering.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private String name;
	
	@Column(name = "customer_street")
	private String street;
	
	@Column(name = "customer_postcode")
	private String postcode;
	
	@Column(name = "customer_username")
	private String username;
	
	@Column(name = "customer_password")
	private String password;
	
	
}
