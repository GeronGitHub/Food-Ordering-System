package com.qa.foodordering.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.qa.foodordering.entity.Food;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class FoodRepositoryTest {
	
	@Autowired
	private FoodRepository foodRepository;

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
	public void given_Food_To_Save_Return_Saved_Food() {
		Food savedFood = foodRepository.save(f1);
		assertNotNull(savedFood);
		assertEquals("FriedChicken", savedFood.getName());
	}
	
	@Test
	@DisplayName("get-food-with-non-existing-id-test")
	public void given_Food_With_Non_Existing_ID_Return_Optional_Empty() {
		foodRepository.save(f1);
		assertThat(foodRepository.findById(321321).isEmpty());
	}
	
	@Test
	@DisplayName("get-food-list-test")
	public void given_AllFood_Return_Food_List() {
		
		//NEW SAVED OBJECT TAKES THE FIRST INDEX POSITION IN LIST
		foodRepository.save(f1); //INDEX POSITION 2
		foodRepository.save(f2); //INDEX POSITION 1
		foodRepository.save(f3); //INDEX POSITION 0
		
		List<Food> foodList = foodRepository.findAll();
		System.out.println(foodList);
		assertEquals(3, foodList.size());
		assertEquals("Chicken", foodList.get(2).getCategory());
		assertEquals(9.99, foodList.get(0).getPrice());
	}
	
	
}
