# VanOptimization


Backend service for optimizing shipment selection for delivery vans.

The application selects the most profitable combination of shipments while respecting the van's maximum volume capacity.
Optimization is implemented using a dynamic programming knapsack algorithm.

---

# Technology Stack

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Flyway
- Gradle
- Docker Compose
- JUnit 5

---

# How To Run The Application

## Clone Repository

git clone https://github.com/YOUR_USERNAME/vanopt.git

cd vanopt

---

## Start PostgreSQL

docker compose up -d

This starts PostgreSQL container with:

- Database: vanopt
- User: postgres
- Password: password
- Port: 5432

---

## Run Application

Run `VanoptApplication` from IntelliJ
or use:

./gradlew bootRun

Application runs on:

http://localhost:8080

---

# Database Setup

Database schema is managed using Flyway migrations.

Migration file:

V1__init_schema.sql

runs automatically when the application starts.

The migration creates:

- optimization_request
- selected_shipment

tables.

---

# Optimization Algorithm

The project uses a dynamic programming knapsack algorithm.

Goal:
- maximize total shipment revenue
- while keeping total shipment volume within van capacity

The algorithm evaluates:
- including shipment
- excluding shipment

for every possible capacity and shipment combination.

---

# API Endpoint

## Optimize Shipments

POST

/api/optimizations

---

# example request

```json
{
  "maxVolume": 15,
  "shipments": [
    {
      "name": "A",
      "volume": 5,
      "revenue": 120
    },
    {
      "name": "B",
      "volume": 10,
      "revenue": 200
    },
    {
      "name": "C",
      "volume": 3,
      "revenue": 80
    }
  ]
}
# Running Tests

Run tests using:

./gradlew test

Tests include:
- optimal shipment selection
- empty result scenarios
- exact capacity fit validation

---

# Database Schema

## optimization_request

Columns:
- id UUID primary key
- max_volume INT
- total_volume INT
- total_revenue INT
- created_at TIMESTAMP

---

## selected_shipment

Columns:
- id BIGINT primary key
- request_id UUID
- name VARCHAR(255)
- volume INT
- revenue INT

---

# Author

Tatuli
