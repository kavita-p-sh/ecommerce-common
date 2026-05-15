# ecommerce-common
ecommerce-common` is a shared common module used across all ecommerce microservices.
This module contains reusable classes such as common entities, DTOs,
exception handling, security utilities, Redis cache configuration, constants, and auditing support.

## Features

-> Common JPA entities
-> Common DTO classes
-> Global exception handling
-> Custom exception classes
-> Redis cache configuration
-> Common constants
-> Utility classes
-> Auditing support using base entity


## Technologies Used
- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- Redis
- Maven

## Project Structure
ecommerce-common
│
├── src/main/java/com/ecommerce/common
│   │
│   ├── config
│   │   └── Common configuration classes
│   │
│   ├── dto
│   │   └── Common request and response DTOs
│   │
│   ├── entity
│   │   └── Common JPA entities
│   │
│   ├── enums
│   │   └── Common enum classes
│   │
│   ├── exception
│   │   └── Custom exceptions and global exception handler
│   │
│   ├── repository
│   │   └── Common Spring Data JPA repositories
│   │
│   └── util
│       └── Constants and utility classes
│
└── pom.xml

## Build Project

bash
mvn clean install

## Purpose

This module is used as a shared dependency for:
- user-service
- product-service
- order-service
