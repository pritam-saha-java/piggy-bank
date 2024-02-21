# piggy-bank: End-to-End Banking Application

## Overview

Piggy Bank is a comprehensive end-to-end banking application developed using Java and Spring Boot. This project incorporates best practices of coding, follows the Singleton design pattern, and adheres to industry-standard coding conventions. Security is a top priority, and the application implements Spring Security for authentication. With a focus on modularity and maintainability, the codebase is organized into separate modules for entities, repositories, services (interfaces and implementations), controllers, configurations, requests, responses, and custom exceptions.

Additionally, Piggy Bank utilizes Docker for containerization, allowing for easy deployment and scalability.

## Features

- **Java and Spring Boot**: Harnessing the capabilities of Java and Spring Boot to create a secure and scalable banking application.

- **MySQL Database**: Utilizing MySQL for efficient data storage and retrieval.

- **Spring Security**: Implementing Spring Security for robust authentication and authorization.

- **Singleton Design Pattern**: Applying the Singleton design pattern for efficient resource management.

- **CRUD Operations**: Enabling a comprehensive set of Create, Read, Update, and Delete operations to manage banking transactions.

- **Modular Structure**: Organizing code into separate modules for entities, repositories, services (interfaces and implementations), controllers, configurations, requests, responses, and custom exceptions.

- **Docker Containerization**: Utilizing Docker to containerize the application for easy deployment and scalability.

## Getting Started

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/pritam-saha-java/piggy-bank.git
   ```

2. **Database Configuration:**
   - Set up your MySQL database and update the `application.properties` file with the appropriate database credentials.

3. **Build and Run:**
   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

4. **Access the Application:**
   Visit `http://localhost:8080` in your web browser to access the Piggy Bank End-to-End Banking Application.

## Docker Containerization

To containerize the application using Docker:

1. **Build the Docker Image:**
   ```bash
   docker build -t piggy-bank-app .
   ```

2. **Run the Docker Container:**
   ```bash
   docker run -p 8080:8080 piggy-bank-app
   ```

3. **Access the Containerized Application:**
   Visit `http://localhost:8080` in your web browser to access the Piggy Bank End-to-End Banking Application running in a Docker container.
