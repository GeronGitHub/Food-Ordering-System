package com.qa.foodordering.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.qa.foodordering.entity.Food;

@ExtendWith(MockitoExtension.class)
public class FoodRepositoryTest {
	
	@Mock
	private FoodRepository foodRepository;
	
	@Autowired
	MockMvc mockMvc;
	
	Food f1;
	Food f2;
	Food f3;
	
	List<Food> foodList;
	
	@BeforeEach
	public void setUp() {
		
		//CREATING THE DATA NEEDED BEFORE EACH TEST CASE
		
		
		f1 = new Food(1, "Chicken", "Fried Chicken", 5.99, false);
		f2 = new Food(2, "Chinese", "Noodles with Black Bean Sauce", 13.99, true);
		f3 = new Food(3, "Dessert", "Chocolate Fudge Cake", 9.99, false);
		foodList = Arrays.asList(f1, f2, f3);
		
	}
	
	@AfterEach
	public void tearDown() {
		f1 = null; f2 = null; f3 = null;
		foodRepository.deleteAll();
		foodList = null;
	}
	
	@Test
	@DisplayName("save-food-test")
	public void given_Food_To_Save_Return_Saved_Food() {
		Food savedFood = foodRepository.save(f1);
		assertNotNull(savedFood);
		assertEquals("Fried Chicken", savedFood.getName());
	}
	
}
