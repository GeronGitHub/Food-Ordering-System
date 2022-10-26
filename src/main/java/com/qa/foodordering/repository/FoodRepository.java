package com.qa.foodordering.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.foodordering.entity.Food;

@Repository
@Transactional
public interface FoodRepository extends JpaRepository<Food, Integer>{
	
	//FIND FOOD BY NAME
	List<Food> findByName(String name);
	
	//FIND FOOD BY CATEGORY
	List<Food> findByCategory(String category);
	
	//FIND FOOD BY PRICE
	@Query("select f from Food f where f.price <= :price")
	List<Food> findByPrice(double price);
	
	//FIND FOOD BY IF IT IS SPICY
	List<Food> findByIsSpicy(boolean isSpicy);
	
	//UPDATING
	@Modifying //FOR UPDATE AND DELETE QUERIES
	@Query("update Food f set f.price = :price where f.id = :id")
	int updateProductDetails(int id, double price);

}
