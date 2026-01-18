# Customer Order Management System

A RESTful API application built with **Spring Boot 3.4.3** for managing customers and their orders. This project demonstrates a complete CRUD implementation with proper exception handling, API documentation, and SQLite database integration.

## 📋 Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [API Documentation](#api-documentation)
- [Database Configuration](#database-configuration)
- [Exception Handling](#exception-handling)
- [Testing](#testing)
- [Contributing](#contributing)

## ✨ Features

- **Customer Management**: Create, retrieve, and delete customer records
- **Order Management**: Create, retrieve, and delete order records
- **One-to-Many Relationship**: Each customer can have multiple orders
- **Global Exception Handling**: Centralized error handling with meaningful error messages
- **API Documentation**: Interactive Swagger/OpenAPI documentation
- **SQLite Database**: Lightweight embedded database for easy setup
- **Spring Boot Actuator**: Monitor and manage application health
- **Lombok Integration**: Reduced boilerplate code with annotations

## 🛠 Technologies Used

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 23 | Programming Language |
| Spring Boot | 3.4.3 | Application Framework |
| Spring Data JPA | - | Data Access Layer |
| Spring Web | - | RESTful Web Services |
| Spring Boot Actuator | - | Application Monitoring |
| SQLite | 3.43.2.1 | Embedded Database |
| Hibernate | 6.6.5 | ORM Framework |
| Lombok | - | Boilerplate Code Reduction |
| SpringDoc OpenAPI | 2.8.4 | API Documentation |
| Maven | - | Build & Dependency Management |

## 📁 Project Structure

```
custorder/
├── src/
│   ├── main/
│   │   ├── java/com/example/custorder/
│   │   │   ├── CustorderApplication.java          # Main Application Entry Point
│   │   │   ├── controller/
│   │   │   │   ├── CustomerController.java        # Customer REST endpoints
│   │   │   │   └── OrderController.java           # Order REST endpoints
│   │   │   ├── Exception/
│   │   │   │   ├── GlobalExceptionHandler.java    # Centralized exception handling
│   │   │   │   ├── ResourceNotFoundException.java # Custom exception for missing resources
│   │   │   │   └── InvalidRequestException.java   # Custom exception for bad requests
│   │   │   ├── model/
│   │   │   │   ├── Customer.java                  # Customer entity
│   │   │   │   └── Order.java                     # Order entity
│   │   │   ├── repo/
│   │   │   │   ├── CustomerRepo.java              # Customer repository interface
│   │   │   │   └── OrderRepo.java                 # Order repository interface
│   │   │   └── services/
│   │   │       ├── CustomerService.java           # Customer business logic
│   │   │       └── OrderService.java              # Order business logic
│   │   └── resources/
│   │       └── application.properties              # Application configuration
│   └── test/
│       └── java/com/example/custorder/
│           └── CustorderApplicationTests.java      # Test cases
├── target/                                         # Compiled classes
├── pom.xml                                         # Maven dependencies
├── mvnw                                            # Maven wrapper (Unix)
├── mvnw.cmd                                        # Maven wrapper (Windows)
└── README.md                                       # Project documentation
```

## 📋 Prerequisites

Before running this application, ensure you have the following installed:

- **Java Development Kit (JDK) 23** or higher
- **Maven 3.6+** (or use the included Maven wrapper)
- **Git** (for cloning the repository)
- An IDE (IntelliJ IDEA, Eclipse, or VS Code recommended)

## 🚀 Installation & Setup

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd custorder
   ```

2. **Build the project using Maven**
   
   **Windows (PowerShell):**
   ```powershell
   .\mvnw.cmd clean install
   ```
   
   **Unix/Linux/Mac:**
   ```bash
   ./mvnw clean install
   ```

3. **Verify the build**
   - Check that the `target/` directory is created
   - Ensure there are no build errors

## ▶️ Running the Application

**Using Maven Wrapper (Recommended):**

Windows (PowerShell):
```powershell
.\mvnw.cmd spring-boot:run
```

Unix/Linux/Mac:
```bash
./mvnw spring-boot:run
```

**Using Java directly:**
```bash
java -jar target/1custmerorder-0.0.1-SNAPSHOT.jar
```

The application will start on **http://localhost:8080**

You should see output similar to:
```
Started CustorderApplication in X.XXX seconds
```

## 📡 API Endpoints

### Customer Endpoints

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| POST | `/api/customers/add` | Create a new customer | `{"name": "John Doe", "email": "john@example.com"}` |
| GET | `/api/customers` | Retrieve all customers | - |
| GET | `/api/customers/{id}` | Retrieve customer by ID | - |
| DELETE | `/api/customers/{id}` | Delete customer by ID | - |

### Order Endpoints

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| POST | `/api/orders/add` | Create a new order | `{"productName": "Laptop", "quantity": 2, "price": 999.99, "customer": {"id": 1}}` |
| GET | `/api/orders` | Retrieve all orders | - |
| GET | `/api/orders/{id}` | Retrieve order by ID | - |
| DELETE | `/api/orders/{id}` | Delete order by ID | - |

### Example API Requests

**Create a Customer:**
```bash
curl -X POST http://localhost:8080/api/customers/add \
  -H "Content-Type: application/json" \
  -d '{"name": "Jane Smith", "email": "jane@example.com"}'
```

**Create an Order:**
```bash
curl -X POST http://localhost:8080/api/orders/add \
  -H "Content-Type: application/json" \
  -d '{"productName": "Smartphone", "quantity": 1, "price": 799.99, "customer": {"id": 1}}'
```

**Get All Customers:**
```bash
curl http://localhost:8080/api/customers
```

**Delete a Customer:**
```bash
curl -X DELETE http://localhost:8080/api/customers/1
```

## 📚 API Documentation

This project includes **Swagger UI** for interactive API documentation.

**Access Swagger UI:**
- **URL**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs

From Swagger UI, you can:
- View all available endpoints
- Test API calls directly in the browser
- See request/response schemas
- View example payloads

## 🗄️ Database Configuration

The application uses **SQLite** as an embedded database for simplicity.

**Configuration (application.properties):**
```properties
spring.datasource.url=jdbc:sqlite:database.db
spring.datasource.driver-class-name=org.sqlite.JDBC
spring.jpa.database-platform=org.hibernate.community.dialect.SQLiteDialect
spring.jpa.hibernate.ddl-auto=update
```

**Database File:**
- The database file `database.db` is automatically created in the project root directory
- Tables are auto-generated based on entity models
- Data persists between application restarts

**Database Schema:**

**Customer Table:**
| Column | Type | Description |
|--------|------|-------------|
| id | BIGINT (PK) | Auto-generated ID |
| name | VARCHAR | Customer name |
| email | VARCHAR | Customer email |

**Order Table:**
| Column | Type | Description |
|--------|------|-------------|
| id | BIGINT (PK) | Auto-generated ID |
| product_name | VARCHAR | Product name |
| quantity | INT | Order quantity |
| price | DOUBLE | Product price |
| customer_id | BIGINT (FK) | Reference to Customer |

## ⚠️ Exception Handling

The application implements **global exception handling** for consistent error responses.

**Custom Exceptions:**
- `ResourceNotFoundException`: Thrown when a requested resource is not found (404)
- `InvalidRequestException`: Thrown for invalid request data (400)

**Error Response Format:**
```json
{
  "timestamp": "2026-01-19T10:30:00",
  "message": "Customer not found with ID: 5",
  "status": 404
}
```

**Exception Handler:**
- `GlobalExceptionHandler` intercepts all exceptions
- Returns structured error responses with HTTP status codes
- Logs exceptions for debugging

## 🧪 Testing

**Run all tests:**

Windows (PowerShell):
```powershell
.\mvnw.cmd test
```

Unix/Linux/Mac:
```bash
./mvnw test
```

**Test Coverage:**
- Basic application context loading test included
- Add integration tests for controllers and services as needed

## 🔧 Spring Boot Actuator

The application includes **Spring Boot Actuator** for monitoring.

**Actuator Endpoints:**
- **Health**: http://localhost:8080/actuator/health
- **Info**: http://localhost:8080/actuator/info
- **All Endpoints**: http://localhost:8080/actuator

All actuator endpoints are enabled by default.

## 📝 Development Notes

**Entity Relationships:**
- `Customer` has a **one-to-many** relationship with `Order`
- Uses `@JsonManagedReference` and `@JsonBackReference` to prevent infinite recursion
- Cascade operations ensure that deleting a customer also deletes associated orders

**Lombok Annotations:**
- `@Getter` / `@Setter`: Generate getters/setters
- `@NoArgsConstructor` / `@AllArgsConstructor`: Generate constructors
- Reduces boilerplate code significantly

**Service Layer Pattern:**
- Business logic is separated into service classes
- Controllers delegate to services
- Repositories handle database operations

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is created as a demonstration/learning project for Spring Boot development.

## 👨‍💻 Author

**College Project - NM 2025 (JAVA With SpringBoot-1)**

## 🙏 Acknowledgments

- Spring Boot Team for the excellent framework
- Spring Data JPA for simplified data access
- SpringDoc for API documentation
- SQLite for the lightweight database solution

---

**Happy Coding! 🚀**
