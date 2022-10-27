package com.qa.foodordering.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.qa.foodordering.entity.Food;
import com.qa.foodordering.exception.FoodAlreadyExistsException;
import com.qa.foodordering.exception.FoodNotFoundException;
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
	
	@GetMapping("/food/{id}")
	public ResponseEntity<?> getFoodByID(@PathVariable("id") int id) throws FoodNotFoundException{
		
		try {
			
			Food foodByID = this.foodService.getFoodByID(id);
			responseEntity = new ResponseEntity<>(foodByID, HttpStatus.OK);
		}
		catch (FoodNotFoundException e) {
			throw e;
		}
		catch (Exception e) {
			
			System.out.println("INTERNAL ERROR HAS OCCURRED.. ");
			e.printStackTrace();
		}
		
		return responseEntity;
	}
	
	@GetMapping("/food/price/{price}")
	public ResponseEntity<?> getFoodByPrice(@PathVariable("price") double price){
		
		try {
			
			List<Food> foodByPrice = this.foodService.getFoodByPrice(price);
			responseEntity = new ResponseEntity<>(foodByPrice, HttpStatus.OK);
		}
		catch (Exception e) {
			
			System.out.println("INTERNAL ERROR HAS OCCURRED.. ");
			e.printStackTrace();
		}
		
		return responseEntity;
	}
	
	@PostMapping("/food")
	public ResponseEntity<?> addFood(@Valid @RequestBody Food food) throws FoodAlreadyExistsException{
		
		try {
			Food addedFood = this.foodService.addFood(food);
			responseEntity = new ResponseEntity<>(addedFood, HttpStatus.CREATED);
		}
		catch (FoodAlreadyExistsException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>("INTERNAL ERROR HAS OCCURRED..", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}
	
	@PutMapping("/food")
	public ResponseEntity<?> updateAllFoodDetails(@RequestBody Food food) throws FoodNotFoundException{
		
		try {
			Food updatedFood = this.foodService.updateAllFoodDetails(food.getId(), food.getCategory(), food.getName(), food.getPrice(), food.isSpicy());
			responseEntity = new ResponseEntity<>(updatedFood, HttpStatus.OK);
		}
		catch (FoodNotFoundException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>("INTERNAL ERROR HAS OCCURRED..", HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return responseEntity;
		
	}
	
	@PutMapping("/food/update_price")
	public ResponseEntity<?> updateFoodPrice(@RequestBody Food food) throws FoodNotFoundException{
		
		try {
			Food updatedFood = this.foodService.updateFoodPrice(food.getId(), food.getPrice());
			responseEntity = new ResponseEntity<>(updatedFood, HttpStatus.OK);
		}
		catch (FoodNotFoundException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>("INTERNAL ERROR HAS OCCURRED..", HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return responseEntity;
		
	}
	
	@DeleteMapping("/food/{id}")
	public ResponseEntity<?> deleteFood(@PathVariable int id) throws FoodNotFoundException{
		
		try {
			boolean status = this.foodService.deleteFood(id);
			responseEntity = new ResponseEntity<>(status, HttpStatus.OK);
		} catch (FoodNotFoundException e) {
			throw e;
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>("INTERNAL ERROR OCCURRED..", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		
		return responseEntity;
	}

}
