# avpcmanagement

Repository for the backend application to manage a civil defense organization. This application is a REST service
that offers the basic features for a manage all the business logic involved in an emergency organization like civil
 defense, police, firefighters and so on.

## Technology

This application is made using springboot and hibernate with mysql database.

## Installation

To install this application you need to install gradle,docker and docker-compose and run the following commands:

    -  gradle build
    -  docker-compose up -d

There is also available a script called create-docker.sh that runs automatically the instructions below.

## Swagger

After run this application a Swagger tool with the documentation is available at http://localhost:8080/swagger-ui.html