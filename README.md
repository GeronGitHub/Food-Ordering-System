# Food-Ordering-System

The objective of this assignment was to create from scratch a web service. Simply put, my project was to allow the customer to be able to order some food online.

### Problem Statement

Create a web service using using Bootstrap with the below mentioned structure
	- Controller layer
	- Service layer
	- Repository layer
	- Multiple Entities
	- Exception handler
	- Service layer
	- Repository layer

### Tech Stack 

This application is build using the following tech stack:
	- Spring Boot 2.7.5
	- Java SE 17
	- Maven 3.8.6
	- MySQL 8.0.31
	- Swagger


### Steps

1. Add some movies data in db.json ( sample movie object is provided in db.json)
2. After adding the movies, run the command `npm run dbserver`
3. Fetch the movies from db.json using fetch API at the API URL `http://localhost:3000/movies`
4. Display these movies in index.html using Bootstrap card component ( as shown in the image )
5. When the user clicks on the favourite icon , add the movie to the favouties using fetch API Post request to the API URL `http://localhost:3000/favourites`
6. If the movie is already favourited, if the user clicks on favourite icon, remove the movie from the favourites in db.json and update the UI
7. If user searches for a specific movie by entering the movie name and clicks on submit button, update the UI with the searched movies list

# End Points
```bash
http://localhost:8080/api/v1/customers
```
```bash
http://localhost:8080/api/v1/orders
```
```bash
http://localhost:8080/api/v1/food
```
```bash
http://localhost:8080/api/v1/signup
```
```bash
http://localhost:8080/api/v1/login/{id}/{pass}
```
```bash
http://localhost:8080/api/v1/orders/{id}
```
```bash
http://localhost:8080/api/v1//food/update_price
```


Above are some of the notable end points.

### Signing Up a new Customer

![Image Not Found](/images/addCustomerImage.png)

### Retrieving all Customers in the database

![Image Not Found](/images/getAllCustomersImage.png)

### Updating details of existing data

![Image Not Found](/images/updateFoodDetailsImage.png)

### Reflection

During this project, there were many obstacles to face and overcome and each one took a level of dedication and research to fully understand the concept and be able to apply it to a unique project. Throughout the implementation of this project, I learned the skills necessary to develop quality code and add structure to ensure a high standard of work produced.