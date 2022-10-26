package com.qa.foodordering.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.foodordering.entity.Food;
import com.qa.foodordering.exception.FoodAlreadyExistsException;
import com.qa.foodordering.exception.FoodNotFoundException;
import com.qa.foodordering.repository.FoodRepository;

@Service
public class FoodServiceImpl implements FoodService {
	
	@Autowired
	FoodRepository foodRepository;

	@Override
	public List<Food> getAllFood() {
		
		return foodRepository.findAll();
	}

	@Override
	public Food getFoodByID(int id) throws FoodNotFoundException {
		
		Optional<Food> optionalFoodFoundByID = foodRepository.findById(id);
		
		if (!optionalFoodFoundByID.isPresent()) {
			throw new FoodNotFoundException();
		}
		
		return optionalFoodFoundByID.get();
	}

	@Override
	public List<Food> getFoodByCategory(String category) {

		return this.foodRepository.findByCategory(category);
	}

	@Override
	public List<Food> getFoodByName(String name){

		return this.foodRepository.findByName(name);
	}

	@Override
	public List<Food> getFoodByPrice(int price) {
		
		return this.foodRepository.findByPrice(price);
	}
	
	@Override
	public List<Food> getFoodByIsSpicy(boolean isSpicy){
		
		return this.foodRepository.findByIsSpicy(isSpicy);
	}
	
	
	@Override
	public Food addFood(Food food) throws FoodAlreadyExistsException {
		
		Optional<Food> optionalFoodFoundByID = this.foodRepository.findById(food.getId());	
		
		if(optionalFoodFoundByID.isPresent()) {
			
			throw new FoodAlreadyExistsException();
		}
		
		return this.foodRepository.save(food);
		
		
	}

	@Override
	public Food updateFood(Food food) throws FoodNotFoundException {
		
		Optional<Food> optionalFoodFoundByID = this.foodRepository.findById(food.getId());	
		
		if(optionalFoodFoundByID.isPresent()) {
			
			throw new FoodNotFoundException();
		}
		
		return this.foodRepository.save(food);
		
	}

	@Override
	public boolean deleteFood(int id) throws FoodNotFoundException {
		boolean status = false;
		
		Optional<Food> optionalFoodFoundByID = this.foodRepository.findById(id);
		
		if(!optionalFoodFoundByID.isPresent()) {
			throw new FoodNotFoundException();
		}
		else {
			this.foodRepository.delete(optionalFoodFoundByID.get());
			status = true;
		}
		
		return status;	
	}

}
