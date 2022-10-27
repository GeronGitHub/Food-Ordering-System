package com.qa.foodordering.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {
	
	private int id;
	private String name;
	private String street;
	private String postcode;
	

}
