<<<<<<< HEAD
# E-commerce Demo Project

This is a simple e-commerce demo application built with Java and Spring Boot.

## Features
- Product management (CRUD)
- RESTful API endpoints
- MVC architecture
- Static homepage

## Project Structure
```
demo/
├── HELP.md
├── mvnw, mvnw.cmd
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/com/ecommerce/demo/
│   │   │   ├── DemoApplication.java
│   │   │   ├── controller/
│   │   │   │   └── ProductController.java
│   │   │   ├── model/
│   │   │   │   └── Product.java
│   │   │   └── service/
│   │   │       └── ProductService.java
│   │   └── resources/
│   │       ├── application.yaml
│   │       ├── static/index.html
│   │       └── templates/
│   └── test/
│       └── java/com/ecommerce/demo/DemoApplicationTests.java
└── target/
```

## Getting Started

### Prerequisites
- Java 17 or later
- Maven 3.6+

### Build and Run

1. **Clone the repository**
2. **Build the project:**
   ```sh
   ./mvnw clean install
   ```
3. **Run the application:**
   ```sh
   ./mvnw spring-boot:run
   ```
4. **Access the homepage:**
   Open [http://localhost:8080](http://localhost:8080) in your browser.

## API Endpoints
- `/api/products` - List all products
- `/api/products/{id}` - Get, update, or delete a product by ID
- `/api/products` (POST) - Create a new product

## License
This project is for demonstration purposes only.
=======
# ecommerce-api
A simple full stack ecommerce application with basic CRUD operations
>>>>>>> d842036d1685f64723c15743e3a0a797df0141ad
