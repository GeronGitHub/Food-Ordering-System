package com.qa.foodordering.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.foodordering.entity.Food;
import com.qa.foodordering.exception.FoodAlreadyExistsException;
import com.qa.foodordering.exception.FoodNotFoundException;

@Service
public interface FoodService {
	
	public List<Food> getAllFood();
	public Food getFoodByID(int id) throws FoodNotFoundException;
	public List<Food> getFoodByCategory(String category);
	public List<Food> getFoodByName(String name);
	public List<Food> getFoodByPrice(double price);
	public List<Food> getFoodByIsSpicy(boolean isSpicy);
	public Food addFood(Food food) throws FoodAlreadyExistsException;
	public Food updateAllFoodDetails(int id, String category, String name, double price, boolean isSpicy) throws FoodNotFoundException;
	public Food updateFoodPrice(int id, double price) throws FoodNotFoundException;
	public boolean deleteFood(int id) throws FoodNotFoundException;
	
}
