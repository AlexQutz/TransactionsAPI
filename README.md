
## Table of Contents

- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [Running Tests](#running-tests)
- [API Endpoints](#api-endpoints)


## Prerequisites

- Docker
- Docker Compose
- Java IDE of choice
- Postman

## Installation
1. Clone the repository:
   
    ```bash
   git clone https://github.com/AlexQutz/TransactionsAPI.git
   cd your-repo

## Running the Application

1. Build and run the application using Docker Compose:

    ```bash
    docker-compose up --build
   
2. The application will be available at http://localhost:8080.

## Running tests

1. To run a specific test open a terminal in your project and run:

   ```bash
   mvn -Dtest=NameOfTheTestYouWantToTest surefire:test
   
## API Endpoints

To test the endpoints via Postman:


1. Transfer Money

 - Endpoint:

   ```http request
   POST /api/transfer
   
- Request Body:

   ```json
   {
   "sourceAccountId": "1",
   "targetAccountId": "2",
   "amount": 10.5,
   "currency": "GBP"
   }
