**Readme for Library Portal**

## Introduction
The Library Management System is a Java-based application built using JavaFX and MySQL. This system provides a user-friendly interface for managing library books, allowing users to search, issue, and return books. The application follows the Model-View-Controller (MVC) architecture for structured development.

## Installation
To run the Library Management System on your local machine, follow these steps:

1. **Clone the Repository:**
   Clone this repository to your local machine using the following command:
   ```
   git clone https://github.com/AfiaElsie/javafx-library-portal.git
   ```

2. **Install Java and JavaFX:**
   Ensure that you have Java Development Kit (JDK) 8 or higher installed on your system. Also, install JavaFX LTS.

3. **Install MySQL:**
   The application requires a MySQL database to store book and user information. Install MySQL on your machine and set up the necessary credentials.

4. **Database Configuration:**
   - Create a new MySQL database named "library management" or any desired name.
   - Import the "library management.sql" file from the repository into your MySQL database to create the required tables and sample data.

5. **Update Database Credentials:**
   In the "src/application/database/DatabaseAction.java" file, update the database connection properties with your MySQL credentials:
   ```
   db.url=jdbc:mysql://localhost:3306/library management
   db.username=root
   db.password=root
   ```

6. **Build the Project:**
   Use your preferred IDE or build tool (such as Maven or Gradle) to build the project.

## Running the Application
Once the installation and setup are complete, follow these steps to run the Library Management System:

1. **Run the Application:**
   - Use your IDE to run the main class: "Start".
   - Alternatively, if you built the project using a build tool, run the application using the generated executable JAR file.

2. **Login Credentials:**
   Use the following credentials to log in to the application:
   - Admin:
     ```
     Username: admin
     Password: admin
     ```
   - User:
     ```
     Username: user
     Password: user
     ```

## System Requirements
The following software should be installed on your system to run the Library Management System:
- Java Development Kit (JDK) 8 or higher
- JavaFX LTS
- MySQL

## Troubleshooting
If you encounter any issues while installing or running the application, please refer to the troubleshooting section in the "FAQ" document included in the repository.

## Contributions
Contributions to the Library Management System are welcome. If you encounter any bugs or have feature suggestions, feel free to open an issue or submit a pull request.

Happy library management! 📚📚📚
