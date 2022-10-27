package com.qa.foodordering.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.foodordering.entity.Food;
import com.qa.foodordering.service.FoodServiceImpl;

@ExtendWith(MockitoExtension.class)
public class FoodControllerTest {
	
	@Mock
	private FoodServiceImpl foodService;
	
	@Autowired
	@InjectMocks
	private FoodController foodController;
	
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
		f3 = new Food(3, "Dessert", "Chocolate Fudge Cakes", 9.99, false);
		foodList = Arrays.asList(f1, f2, f3);
		
		mockMvc = MockMvcBuilders.standaloneSetup(foodController).build();
		
	}
	
	@AfterEach
	public void tearDown() {
		f1 = f2 = f3 = null;
		foodList = null;
	}
	
	@Test
	@DisplayName("save-food-test")
	public void given_Food_To_Save_Food_Should_Return_Food_As_JSON_With_Status_Create() throws Exception{
		when(foodService.addFood(any())).thenReturn(f1);
		mockMvc.perform(post("/api/v1/food")
		        .contentType(MediaType.APPLICATION_JSON)
		        .content(asJsonString(f1)))
        		.andExpect(status().isCreated())
        		.andExpect(jsonPath("$.category").value("Chicken"));
				
	}
	
	@Test
	@DisplayName("get-foods-test")
	public void given_AllFoods_Should_Return_List() throws Exception {
		when(foodService.getAllFood()).thenReturn(foodList);
		mockMvc.perform(get("/api/v1/food")
				        .accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[1].price").value(13.99));
	}
	
	public static String asJsonString(Object obj) {
		ObjectMapper Obj = new ObjectMapper();
		String jsonStr = null;
		
        try {

            jsonStr = Obj.writeValueAsString(obj);
            System.out.println(jsonStr);
        }
        catch (IOException e) {
        	System.out.println("SOME INTERNAL ERROR HAS OCCURED..");
            e.printStackTrace();
        }
        return jsonStr;
	}

}
