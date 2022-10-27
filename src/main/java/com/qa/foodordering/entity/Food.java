package com.qa.foodordering.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "food_details")
public class Food {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "food_id")
	private int id;
	
	@NotNull
	@Pattern(regexp = "^[A-Za-z]*", message = "Invalid category - must only contain alphabetical characters !!")
	@Size(min = 2, max = 20, message = "Category must be between 2 and 20 characters long !!")
	@Column(name = "food_category")
	private String category; //CATEGORIES INCLUDE: CHICKEN, CHINESE, PIZZA, KEBAB, DESSERTS, ENGLISH
	
	@NotNull
	@Pattern(regexp = "^[A-Za-z ]*", message = "Invalid name - must only contain alphabetical characters !!")
	@Size(min = 2, max = 50, message = "Name must be between 2 and 20 characters long !!")
	@Column(name = "food_name")
	private String name;
	
	@NotNull
	@Min(0)
	@Column(name = "food_price")
	private double price;
	
	@Column(name = "food_isSpicy")
	private boolean isSpicy;

}
