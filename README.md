# Food-Ordering-System

The objective of this assignment was to create from scratch a web service. Simply put, my project was to allow the customer to be able to order some food online.
### Problem Statement

Create a web service using using Bootstrap with the structure:
1. Controller layer
2. Service layer
3. Repository layer
4. Multiple Entities
5. Exception handler


### Tech Stack 

This application is build using the following tech stack:
	- Spring Boot 2.7.5
	- Java SE 17
	- Maven 3.8.6
	- MySQL 8.0.31
	- Swagger


### Steps

1. Create Customers by using the end point provided below, sending it as a POST request.
2. Repeat step 1 to create an Order for your customer to allow them to add their food order.
3. Fetch the data of any given customer using a GET request with the end point "http://localhost:8080/api/v1/customers/{id}"
4. To remove any given data, use the DELETE mapping and the end point "http://localhost:8080/api/v1/customers/{id}", where "customers" can be replaced with "orders" or "food".
5. For any other given operation you wish to accomplish, use the multitude of end points to be able to implement a functionality to the database.

# End Points

Below are some of the notable end points.

```bash
http://localhost:8080/api/v1/customers
```

```bash
http://localhost:8080/api/v1/orders
```
```bash
http://localhost:8080/api/v1/food
```
The above end points are all used for get, post and put mappings to be able to get or update each object in that field or if it doesn't exist then to create a new object.

```bash
http://localhost:8080/api/v1/signup
```

The sign up post mapping is used to create a customer and allow for a user to essentially create their account to be able to order.

```bash
http://localhost:8080/api/v1/login/{id}/{pass}
```
```bash
http://localhost:8080/api/v1/orders/{id}
```
These two above end points both have parameters required to be able to retrieve the data when there are many instances of accounts or orders that need to be filtered out.

```bash
http://localhost:8080/api/v1//food/update_price
```


### Signing Up a new Customer

![Image Not Found](/images/addCustomerImage.png)

### Retrieving all Customers in the database

![Image Not Found](/images/getAllCustomersImage.png)

### Updating details of existing data

![Image Not Found](/images/updateFoodDetailsImage.png)

### Reflection

During this project, there were many obstacles to face and overcome and each one took a level of dedication and research to fully understand the concept and be able to apply it to a unique project. Throughout the implementation of this project, I learned the skills necessary to develop quality code and add structure to ensure a high standard of work produced.