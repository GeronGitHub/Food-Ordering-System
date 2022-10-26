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
@Table(name = "order_details")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private int id;
	
	@Column(name = "order_date")
	private String date;
	
	@Column(name = "order_status")
	private String status; //ACCEPTED, DENIED, PROCESSING
	
	@Column(name = "order_value")
	private double value;
	
	@Column(name = "order_customer_id")
	private int customerID;
	
	@Column(name = "order_food_id")
	private int foodID;
	
	@Column(name = "order_delivered")
	private boolean delivered;
	
}
