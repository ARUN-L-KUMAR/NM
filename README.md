# E-Commerce REST API

A comprehensive RESTful API built with Spring Boot for managing customers, products, and orders in an e-commerce system. This API uses SQLite as the database with a custom Hibernate dialect for seamless integration.

## 🚀 Features

- **Customer Management**: Create, read, update, and delete customer records
- **Product Management**: Full CRUD operations for products with inventory tracking
- **Order Management**: Complete order lifecycle management with status tracking
- **SQLite Database**: Lightweight, file-based database with custom Hibernate dialect
- **Data Validation**: Input validation using Bean Validation API
- **Exception Handling**: Centralized error handling with meaningful responses
- **Sample Data**: Auto-initialization with sample customers and products

## 🛠️ Technology Stack

- **Java**: 17
- **Spring Boot**: 2.7.18
- **Spring Data JPA**: Database persistence
- **Hibernate**: 5.6.15.Final
- **SQLite**: Lightweight embedded database
- **Maven**: Build and dependency management
- **Jackson**: JSON processing

## 📋 Prerequisites

- JDK 17 or higher
- Maven 3.6+
- Git (for cloning the repository)

## 🔧 Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/ARUN-L-KUMAR/NM.git
cd NM
```

### 2. Build the Project

```bash
mvn clean install
```

### 3. Run the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## 📊 Database Configuration

The application uses SQLite with a custom Hibernate dialect. The database file (`database.db`) is automatically created in the project root directory.

### Custom SQLite Dialect

This project includes a custom `SQLiteDialect` class to ensure compatibility with Hibernate 5.6.x:
- Located at: `src/main/java/com/example/ecommerce/config/SQLiteDialect.java`
- Handles SQLite-specific SQL generation
- Supports identity column generation

## 🌐 API Endpoints

### Customer Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/customers` | Create a new customer |
| GET | `/api/customers` | Get all customers |
| GET | `/api/customers/{id}` | Get customer by ID |
| PUT | `/api/customers/{id}` | Update customer |
| DELETE | `/api/customers/{id}` | Delete customer |
| GET | `/api/customers/search?email={email}` | Search by email |
| GET | `/api/customers/search/name?name={name}` | Search by name |

### Product Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/products` | Create a new product |
| GET | `/api/products` | Get all products |
| GET | `/api/products/{id}` | Get product by ID |
| PUT | `/api/products/{id}` | Update product |
| DELETE | `/api/products/{id}` | Delete product |
| GET | `/api/products/search?keyword={keyword}` | Search products |
| GET | `/api/products/search/name?name={name}` | Search by name |
| GET | `/api/products/category/{category}` | Get by category |
| GET | `/api/products/brand/{brand}` | Get by brand |
| GET | `/api/products/price-range?min={min}&max={max}` | Filter by price |
| GET | `/api/products/low-stock?threshold={threshold}` | Get low stock items |
| PUT | `/api/products/{id}/stock?quantity={quantity}` | Update stock |
| GET | `/api/products/active` | Get active products |

### Order Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/orders` | Create a new order |
| GET | `/api/orders` | Get all orders |
| GET | `/api/orders/{id}` | Get order by ID |
| GET | `/api/orders/customer/{customerId}` | Get orders by customer |
| GET | `/api/orders/status/{status}` | Get orders by status |
| PUT | `/api/orders/{id}/status` | Update order status |
| PUT | `/api/orders/{id}/total?amount={amount}` | Update total amount |
| PUT | `/api/orders/{id}/cancel` | Cancel order |
| DELETE | `/api/orders/{id}` | Delete order |
| GET | `/api/orders/date-range?start={start}&end={end}` | Get orders by date |
| GET | `/api/orders/recent?days={days}` | Get recent orders |
| GET | `/api/orders/total-sales` | Get total sales |
| GET | `/api/orders/count/status/{status}` | Count orders by status |

## 📝 Request Examples

### Create a Customer

```bash
POST http://localhost:8080/api/customers
Content-Type: application/json

{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phone": "+1234567890",
  "address": "123 Main St, New York, NY 10001"
}
```

### Create a Product

```bash
POST http://localhost:8080/api/products
Content-Type: application/json

{
  "name": "iPhone 15",
  "description": "Latest iPhone with advanced features",
  "price": 999.99,
  "stockQuantity": 50,
  "category": "Electronics",
  "brand": "Apple"
}
```

### Create an Order

```bash
POST http://localhost:8080/api/orders
Content-Type: application/json

{
  "customerId": 1,
  "shippingAddress": "123 Main St, New York, NY 10001",
  "totalAmount": 999.99
}
```

## 🗂️ Project Structure

```
nm2/
├── src/
│   ├── main/
│   │   ├── java/com/example/ecommerce/
│   │   │   ├── config/
│   │   │   │   ├── DataInitializer.java
│   │   │   │   ├── SQLiteDialect.java
│   │   │   │   └── SQLiteIdentityColumnSupport.java
│   │   │   ├── controller/
│   │   │   │   ├── CustomerController.java
│   │   │   │   ├── OrderController.java
│   │   │   │   └── ProductController.java
│   │   │   ├── entity/
│   │   │   │   ├── Customer.java
│   │   │   │   ├── Order.java
│   │   │   │   ├── OrderStatus.java
│   │   │   │   └── Product.java
│   │   │   ├── repository/
│   │   │   │   ├── CustomerRepository.java
│   │   │   │   ├── OrderRepository.java
│   │   │   │   └── ProductRepository.java
│   │   │   ├── service/
│   │   │   │   ├── CustomerService.java
│   │   │   │   ├── OrderService.java
│   │   │   │   └── ProductService.java
│   │   │   └── EcommerceApiApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── target/
├── pom.xml
└── README.md
```

## 🎯 Order Status

Orders can have the following statuses:
- `PENDING`: Order placed, awaiting processing
- `CONFIRMED`: Order confirmed by seller
- `PROCESSING`: Order is being prepared
- `SHIPPED`: Order has been shipped
- `DELIVERED`: Order delivered to customer
- `CANCELLED`: Order cancelled

## 🔍 Sample Data

On first startup, the application automatically creates:
- **3 Customers** with complete profiles
- **4 Products** across different categories (Electronics, Computers, Audio)

## ⚙️ Configuration

### Application Properties

```properties
server.port=8080
spring.datasource.url=jdbc:sqlite:database.db
spring.datasource.driver-class-name=org.sqlite.JDBC
spring.jpa.database-platform=com.example.ecommerce.config.SQLiteDialect
spring.jpa.hibernate.ddl-auto=update
```

## 🐛 Troubleshooting

### SQLite Dialect Issues

If you encounter SQLite dialect errors, ensure:
1. The custom `SQLiteDialect.java` is present in the config package
2. The `application.properties` points to the custom dialect
3. Maven dependencies are properly downloaded

### Port Already in Use

If port 8080 is occupied:
1. Stop the application using that port
2. Or change the port in `application.properties`:
   ```properties
   server.port=8081
   ```

## 📄 License

This project is open-source and available for educational purposes.

## 👨‍💻 Author

**Arun Kumar**
- GitHub: [@ARUN-L-KUMAR](https://github.com/ARUN-L-KUMAR)

## 🤝 Contributing

Contributions, issues, and feature requests are welcome!

## 📞 Support

For support or questions, please open an issue in the GitHub repository.

---

**Built with ❤️ using Spring Boot**
