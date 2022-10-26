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
@Table(name = "food_details")
public class Food {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "food_id")
	private int id;
	
	@Column(name = "food_category")
	private String category; //CATEGORIES INCLUDE: CHICKEN, CHINESE, PIZZA, 
							 //KEBAB, DESSERTS, ENGLISH
	
	@Column(name = "food_name")
	private String name;
	
	@Column(name = "food_price")
	private double price;
	
	@Column(name = "food_isSpicy")
	private boolean isSpicy;

}
