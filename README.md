## Prerequisite
- Java 11
- Maven 3.6 (Tested compatible version)

## Setup Guide
To run the application in Dev mode:

1. Run MySQL container on docker
```
docker run --name demo -p 3306:3306 -e MYSQL_DATABASE=demo -e MYSQL_ROOT_PASSWORD=password -d mysql:8
```

2. Run the following command to start the application
```
mvn spring-boot:run
```

## Swagger Documentation

Navigate to http://localhost:8080/swagger-ui/index.html after starting the application.

