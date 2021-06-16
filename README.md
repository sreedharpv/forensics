# Forensics application

### Assumption: 
* Person's directions found in the map. Considering that, directions has been taken in a static CSV data file
* Implemented Spring Security to identify the valid emailid
* Directions API can call freely, however directions data would be same for any number of 'directions' calls. Hence loaded the data once during server startup and kept in inmemory to avoid further downstream API calls
* Followed production ready coding standards 

## Step 1:
Require to install Java Version 11

## Step 2:
Checkout the code from branch 'origin/main' from the repo

https://github.com/sreedharpv/forensics

## Step 3: 
use below commands to run the application:

1) mvn clean install to down load all dependencies or reload all maven projects from IntelliJ IDE 

2) Right click on ForensicsApplication.java and run the application as standalone 

## Step 4:
Get the postman collection (Which.postman_collection.json) from below folder

https://github.com/sreedharpv/forensics

 Get the Swagger JSON from below url:
* http://localhost:8082/api/v2/api-docs
* Copy the JSON on https://editor.swagger.io/

![GitHub Logo](https://github.com/sreedharpv/forensics/blob/main/Swagger.png)


## Step 5: 
## API details
1) /api/login API should be the first API call to get the authorization token
2) Use this authorization token as Authorization header for business APIs

#### API 1: http://localhost:8082/api/login
  * Request Header: 
      * Content-Type: application/json
  * Body Payload: 
    {
      "emailId": "my email Id",
      "password": "my password"
    }
  * Response Headers:
  authorization
  
    ex: http://localhost:8082/api/login
  
#### API 2: /api/{email}/directions 
  * Request Headers:
      * Content-Type: application/json
      * Authorization: Copy from login API
    
    ex: http://localhost:8082/api/sreedhar@abc.com/directions
    
#### API 3:/api/{email}/location/{x}/{y}
  * Request Headers:
    * Content-Type: application/json
    * Authorization: Copy from login API
    
    ex:http://localhost:8082/api/email@email.com/location/4/3
    
#### Other Acutator APIs:
* http://localhost:8082/api/actuator
* http://localhost:8082/api/actuator/health

# Key Techonoligies/functionalities covered:
* JUnit Test cases
* Integration Testing
* Spring Secuirty 
* InMemory Cache to check the no of API calls. Configured 5 mins expiry time for Cached object. API will start functioning after 5 mins of cache expirty.
* Swagger
* Actuator
* Exception Handling using RestControllerAdvice
* Loading the data file during server start up to avoid multiple downstream api calls
* Validations
