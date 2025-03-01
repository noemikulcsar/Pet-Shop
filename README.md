# 🛒 Order Management System

## 📋 Overview
This application is a comprehensive order management system with a graphical user interface. It handles customer and product information, order placement, invoice generation, and stock management.

## ✨ Key Features

### 👥 Customer Management
- Add new customers with personal details (name, address, email, age)
- Edit existing customer information
- Remove customers from the system
- View all customers in an intuitive list format

### 📦 Product Management
- Add new products with details and available stock
- Update product information
- Delete products from the system
- View all products in the inventory

### 🧾 Order Processing
- Place orders by selecting customers and products
- Specify quantities for ordered items
- Automatic stock verification during order placement
- Real-time inventory updates when orders are placed

### 📃 Invoice Management
- Automatic invoice generation for completed orders
- PDF format invoices for easy downloading and printing
- Detailed order information included in each invoice

## 🖥️ User Interface
- Clean and intuitive Swing-based interface
- Organized panels for different operations:
  - ClientsOperationsPanel for customer management
  - ProductsOperationsPanel for product management
  - OrdersPanel for placing orders and generating invoices
- User-friendly forms for data entry and manipulation

## 🏗️ Architecture

The application follows an Object-Oriented Programming (OOP) approach with a clear separation of concerns:

### Business Logic Layer
- ClientBLL for customer-related business logic
- ProductBLL for product-related business logic
- OrderBLL for order-related business logic

### Data Access Layer
- ClientDAO for customer data operations
- ProductDAO for product data operations
- OrderDAO for order data operations
- BillDAO for invoice data operations

### Presentation Layer
- Swing-based GUI components for user interaction
- Controllers to handle user input and update views

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- MySQL Database
- Maven (optional, for dependency management)

## 🔗 References
- Java JDBC: https://www.baeldung.com/java-jdbc
- MySQL Connection: http://www.mkyong.com/jdbc/how-to-connect-to-mysql-with-jdbc-driver-java/
- Enterprise Architecture: https://dzone.com/articles/layers-standard-enterprise
- Java Reflection: http://tutorials.jenkov.com/java-reflection/index.html
- PDF Creation: https://www.baeldung.com/java-pdf-creation
