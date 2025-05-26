# Learning Project: JSP, Java, MariaDB, and Tomcat

This project was developed with the aim of exploring and understanding the integration of Java web technologies, including JavaServer Pages (JSP), Java (Servlets), a MariaDB database (accessed via ODBC), and the Apache Tomcat application server.

## Technologies Used

* **Java Development Kit (JDK):** Version 21 or higher (recommended).
* **Apache Tomcat:** Web application server for running JSPs and Servlets (version 10.1 or higher).
* **MariaDB:** Relational database management system.
* **MariaDB Connector/J (JDBC Driver):** For Java connection to MariaDB. (Note: Although the initial mention was ODBC, for Java, JDBC is the standard and most recommended. This README assumes the use of JDBC for the Java-MariaDB connection).
* **JSP (JavaServer Pages):** For the dynamic presentation layer.
* **Java Servlets:** For business logic and control.
* **ODBC (Open Database Connectivity):** (If applicable for external tools or specific configuration, but for Java connection to MariaDB, JDBC is the standard driver).

## Project Structure

The project follows a typical Java web application structure:

* `src/main/java`: Contains `.java` files (Servlets, model classes, etc.).
* `src/main/webapp`: Contains web files, including:
    * `WEB-INF`: Web application configuration directory.
        * `web.xml`: Application deployment descriptor.
        * `lib`: Where the MariaDB JDBC driver should be placed.
    * `.jsp`: JSP files for web pages.
    * `.html`, `.css`, `.js`: Other static web resources.

### Architectural Pattern

This project adheres to the **Model-View-Controller (MVC)** architectural pattern, which helps in organizing the codebase and separating concerns:

* **Model:** Represents the application's data, business logic, and rules. In this project, Java classes that interact with the MariaDB database (e.g., Data Access Objects - DAOs, or simple POJOs representing entities like `Department` and `Employee`) serve as the Model. They handle data storage, retrieval, and manipulation.
* **View:** Responsible for displaying the data to the user and capturing user input. JavaServer Pages (JSP) files act as the View, rendering dynamic web content based on data provided by the Controller.
* **Controller:** Acts as an intermediary between the Model and the View. It receives user requests, processes them by interacting with the Model (e.g., calling business logic or fetching data), and then selects the appropriate View to display the results. Java Servlets typically play the role of the Controller in this type of application.

This separation makes the application more modular, easier to maintain, and scalable.

## Environment Setup

### 1. JDK Installation

Ensure you have the JDK installed and the `JAVA_HOME` environment variable correctly configured.

### 2. Apache Tomcat Installation

Download and install Apache Tomcat (version 10.1 or higher is generally compatible with JDK 21+).

### 3. MariaDB Installation

Install MariaDB on your system.

### 4. Database Configuration

Create a database and the necessary tables for the project in MariaDB. Example DDL (Data Definition Language) for the `department` and `employee` tables:

```sql
-- Create the 'department' table
CREATE TABLE department (
    dep_id VARCHAR(36) PRIMARY KEY,
    dep_name VARCHAR(50)
);

-- Create the 'employee' table
CREATE TABLE employee (
    emp_id VARCHAR(36) PRIMARY KEY,
    emp_name VARCHAR(50),
    dep_id VARCHAR(36),
    registration_datetime TIMESTAMP,
    FOREIGN KEY (dep_id) REFERENCES department(dep_id)
);

-- Create an index on the dep_id column of the 'employee' table
CREATE INDEX idx_employee_dep_id ON employee (dep_id);
