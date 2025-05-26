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
```

### 5. MariaDB JDBC Driver

Download the MariaDB Connector/J (the JDBC driver) from the official MariaDB website. Place the downloaded `.jar` file in your projects `src/main/webapp/WEB-INF/lib` folder (or in the `lib` folder of your Tomcat installation, if you prefer a global approach, although the former is recommended for project portability).

### 6. Database Connection Configuration (Java Example)

Within your Java code (Servlets or utility classes), you will need to configure the database connection string.

```java

// Example JDBC connection string for MariaDB
String url = "jdbc:mariadb://localhost:3306/your_database_name"; // Replace 'your_database_name'
String user = "your_username"; // Replace 'your_username'
String password = "your_password"; // Replace 'your_password'

try {
    // Load the JDBC driver (necessary for older JDBC versions, but good practice)
    Class.forName("org.mariadb.jdbc.Driver");
    // Establish the connection
    Connection conn = DriverManager.getConnection(url, user, password);
    // ... use the connection
} catch (ClassNotFoundException e) {
    e.printStackTrace();
    // Handle error: Driver not found
} catch (SQLException e) {
    e.printStackTrace();
    // Handle error: Connection or SQL operation failed
}
```

## How to Run the Project

1.  **Build the Project:** If you are using an IDE like Eclipse or IntelliJ IDEA, import the project and build it (a `.war` file is usually created).
2.  **Deploy to Tomcat:** Copy the generated `.war` file to the `webapps` folder of your Apache Tomcat installation.
3.  **Start Tomcat:** Navigate to the `bin` directory of your Tomcat installation and execute `startup.bat` (Windows) or `startup.sh` (Linux/macOS).
4.  **Access the Application:** Open your browser and access your application's URL (e.g., `http://localhost:8080/your_project_name/`).

## Utility Scripts

This project includes a PowerShell script to help configure your Java environment:

* **`javaconfig.ps1`**: Located in the `scripts` directory. This script automates the process of setting `JAVA_HOME` and updating the system `Path` environment variable to point to the latest JDK installed in `C:\Java`.

    **How to Run `javaconfig.ps1`:**

    1.  Open PowerShell as an **Administrator**.
    2.  Navigate to the `scripts` directory of your project:

        ```powershell
        cd C:\path\to\your\project\scripts
        ```

        (Replace `C:\path\to\your\project` with the actual path to your project.)
    3. Execute the script:
        ```powershell
            .\javaconfig.ps1
        ```
    4. After execution, restart your terminal or IDE for the changes to the environment variables to take effect.

## Contribution

Feel free to explore, modify, and add new functionalities to this project to enhance your learning.