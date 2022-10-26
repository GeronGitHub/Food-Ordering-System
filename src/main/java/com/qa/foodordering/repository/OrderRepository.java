package com.qa.foodordering.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.foodordering.entity.Order;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, Integer>{
	
	//FIND ORDER BY DATE
	List<Order> findByDate(String date);
	
	//FIND ORDER BY CUSTOMER ID
	List<Order> findByCustomerID(int customerID);
	
	//FIND ORDER BY ORDER STATUS
	List<Order> findByStatus(String status);
	
	//FIND ORDER BY ORDER DELIVERED
	List<Order> findByDelivered(boolean delivered);
	
	//FIND ORDER BY VALUE
	List<Order> findByValue(double value);
	
	//UPDATING ALL ORDER DETAILS
	@Modifying
	@Query("update Order o set o.date = :date, o.customerID = :customerID, o.status = :status, o.delivered = :delivered, o.value = :value where o.id = :id")
	int updateAllOrderDetails(int id, String date, int customerID, String status, boolean delivered, double value);
	
	//UPDATING ORDER STATUS AND DELIVERY NOTICE
	@Modifying
	@Query("update Order o set o.status = :status, o.delivered = :delivered where o.id = :id")
	int updateOrderStatusAndDelivery(int id, String status, boolean delivered);

}
