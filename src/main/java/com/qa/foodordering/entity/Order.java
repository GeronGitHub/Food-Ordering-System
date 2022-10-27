package com.qa.foodordering.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
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
@Table(name = "order_details")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private int id;
	
	@Column(name = "order_date")
	@NotNull
	@Pattern(regexp = "^[a-zA-Z0-9-]*$", message = "Invalid date - must be in the form DD-MM-YYYY OR D/M/YY!!")
	@Size(min = 6, max = 10, message = "Date must be between 2 and 20 characters long !!") //1-1-01 is the lowest amount of characters for a date and 31-12-2022 is the highest
	private String date;
	
	@Column(name = "order_status")
	@NotNull
	@Pattern(regexp = "^[A-Za-z]*", message = "Invalid status - must only contain alphabetical characters !!")
	@Size(min = 6, max = 10, message = "Status must be between 6 and 20 characters long (ACCEPTED, DENIED, PROCESSING)!!")
	private String status; //ACCEPTED, DENIED, PROCESSING
	
	@Column(name = "order_value")
	@Min(0)
	private double value;
	
	@Column(name = "order_delivered")
	private boolean delivered;
	
	@Column(name = "customer_id")
	@Min(0)
	private int customerID;
	
	//ADD ONE TO MANY MAPPING (ONE ORDER HAS MANY FOOD)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "ordered_food", joinColumns = {
			@JoinColumn(name = "order_id", referencedColumnName = "order_id")
	}, inverseJoinColumns = {
			@JoinColumn(name = "food_id", referencedColumnName = "food_id")
	})
	private List<Food> foodList;
	
}
