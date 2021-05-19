# API_BiddingSystem
This is a Spring boot application with two microservices that only uses REST API's to make a bidding system. 


## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)


## General info

There are three `microservices` BiddingSystem, BiddingSystemUsers and BiddingSystemgateway,the firts one has all the endpoints related to the products which will be set for bid,
the second one is used to sign up and manage users and the last one is the common gateway to call both the microservices. Both of them are run on different ports. `Users` need to sign up to access any of the product related endpoints. `Admins` have the privilege to add other users as Admins and add products for bidding. There is role based `authorization` and `authentication` from database.
Swagger 2 is used to document the API endpoints, you can see the documentation by visiting the `swagger-ui.html` page. Postman is used to test the endpoints.


## Technologies
* Eclipse
* Jdk 8 or above
* [Spring boot with web,security and jpa starter dependencies](https://start.spring.io/)
* [Swagger 2](https://swagger.io/)
* [Postman](https://www.postman.com/)
* MySQL


## Setup

Go to the `root directory` of the project and open Command Prompt.

Type `mvn clean package` press enter and you should have two executable jar file in the directory.

Execute the jar file having `.original` as a suffix using `java -jar $jarfile`
