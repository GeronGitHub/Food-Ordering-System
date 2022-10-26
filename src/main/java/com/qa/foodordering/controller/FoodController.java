package com.qa.foodordering.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.foodordering.entity.Food;
import com.qa.foodordering.service.FoodServiceImpl;

@RestController
@RequestMapping("api/v1")
public class FoodController {
	
	@Autowired
	FoodServiceImpl foodService;
	
	ResponseEntity<?> responseEntity;
	
	@GetMapping("/food")
	public ResponseEntity<?> getAllFood(){
		
		try {
			
			List<Food> foodList = this.foodService.getAllFood();
			responseEntity = new ResponseEntity<>(foodList, HttpStatus.OK);
		}
		catch (Exception e) {
			
			System.out.println("INTERNAL ERROR HAS OCCURRED.. ");
			e.printStackTrace();
		}
		
		return responseEntity;
	}

}
