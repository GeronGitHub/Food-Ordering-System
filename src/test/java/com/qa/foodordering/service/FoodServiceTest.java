package com.qa.foodordering.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.qa.foodordering.entity.Food;
import com.qa.foodordering.exception.FoodAlreadyExistsException;
import com.qa.foodordering.repository.FoodRepository;


@ExtendWith(MockitoExtension.class)
public class FoodServiceTest {

	@Mock //creates the mock object
	private FoodRepository foodRepository;
	
	@Autowired
	@InjectMocks
	private FoodServiceImpl foodService;
	
	Food f1;
	Food f2;
	Food f3;
	
	List<Food> foodList;
	
	@BeforeEach
	public void setUp() {
		
		//CREATING THE DATA NEEDED BEFORE EACH TEST CASE
		
		
		f1 = new Food(1, "Chicken", "Fried Chicken", 5.99, false);
		f2 = new Food(2, "Chinese", "Noodles with Black Bean Sauce", 13.99, true);
		f3 = new Food(3, "Dessert", "Chocolate Fudge Cakes", 9.99, false);
		foodList = Arrays.asList(f1, f2, f3);
		
	}
	
	@AfterEach
	public void tearDown() {
		f1 = f2 = f3 = null;
		foodRepository.deleteAll();
		foodList = null;
	}
	
	@Test
	@DisplayName("save-food-test")
	public void given_Product_To_Save_Return_Saved_Product() throws FoodAlreadyExistsException{
		when(foodRepository.findById(any())).thenReturn(Optional.empty());
		when(foodRepository.save(any())).thenReturn(f1);
		
		Food savedFood = foodService.addFood(f1);
		
		assertNotNull(savedFood);
		assertEquals(1, savedFood.getId());
		
	}
	
	@Test
	@DisplayName("save-food-test-throws-exception")
	public void given_Existing_Food_To_Save_Should_Throw_FoodAlreadyExistsException() throws FoodAlreadyExistsException{
		
		when(foodRepository.findById(any())).thenReturn(Optional.of(f1));
		
		assertThrows(FoodAlreadyExistsException.class, () -> foodService.addFood(f1));

	} 
	
}
