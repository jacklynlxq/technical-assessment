Run MySQL container on docker
```
docker run --name demo -p 3306:3306 -e MYSQL_DATABASE=demo -e MYSQL_ROOT_PASSWORD=password -d mysql:8
```

Run 
```
mvn spring-boot:run
```

TODO:
- repository
- dto
- resource
