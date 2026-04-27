# TaskTracker - Backend API

The backend service for the TaskTracker full-stack application. This is a secure, high-performance RESTful API built with Spring Boot 3 and Java 17, designed to manage tasks, enforce role-based access, and handle passwordless authentication via OAuth 2.0.

## 🛠 Tech Stack

**Core Architecture:**
* **Language:** Java 17
* **Framework:** Spring Boot (v3.5.6)
* **Build Tool:** Maven

**Data & Persistence:**
* **Database:** MySQL
* **ORM & Data Access:** Spring Data JPA, Spring Data JDBC
* **Driver:** MySQL Connector/J

**Security & Authentication:**
* **Core Security:** Spring Security
* **SSO Integration:** Spring Boot OAuth2 Client (Google & GitHub)
* **Token Management:** JJWT (JSON Web Tokens - v0.12.6) for stateless session handling

**Utilities & Deployment:**
* **Boilerplate Reduction:** Lombok
* **Data Validation:** Spring Boot Starter Validation
* **Notifications:** Spring Boot Starter Mail (SMTP)
* **Deployment:** Azure WebApp Maven Plugin

## ✨ Key Features

* **Passwordless OAuth2 Login:** Seamless SSO integration with Google and GitHub, eliminating the need for traditional password storage.
* **Stateless JWT Authentication:** Generates and validates secure JSON Web Tokens for API request authorization.
* **Role-Based Access Control (RBAC):** Strict endpoint protection ensuring standard users can only access their personal data, while granting administrators broader directory management capabilities.
* **Relational Data Modeling:** Efficient data persistence and entity relationship management utilizing Spring Data JPA and Hibernate.
* **Automated Email Notifications:** Integrated SMTP services for user alerts and directory communications.

## 🚀 Local Development Setup

To run this backend application locally, ensure you have Java 17, Maven, and a local instance of MySQL Server installed.

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/Rahulkumarsingh17898/TaskTracker-Backend.git](https://github.com/Rahulkumarsingh17898/TaskTracker-Backend.git)
