# Client Data Services with JAX-RS

An example project demonstrating how to configure and deploy a standalone embedded web server in Java using Jetty to expose Client Data Services using JAX-RS 

## Usage

```bash
mvn clean package
java -jar embedded-cds.jar
```

### Linux

```bash
curl http://localhost:8080/services/note
curl http://localhost:8080/services/note/1
curl -v -X POST -H "Content-Type: application/json" -d '{"title":"TITLE", "description":"DESC"}' http://localhost:8080/services/note
curl -v -X PUT -H "Content-Type: application/json" -d '{"id":"1", "title":"TITLE UPDATE", "description":"DESC UPDATE"}' http://localhost:8080/services/note/1
curl -v -X DELETE http://localhost:8080/services/note/1
```

### Windows

```bash
curl http://localhost:8080/services/note
curl http://localhost:8080/services/note/1
curl -v -X POST -H "Content-Type: application/json" -d "{\"title\":\"TITLE\", \"description\":\"DESC\"}" http://localhost:8080/services/note
curl -v -X PUT -H "Content-Type: application/json" -d "{\"id\":\"1\", \"title\":\"TITLE UPDATE\", \"description\":\"DESC UPDATE\"}" http://localhost:8080/services/note/1
curl -v -X DELETE http://localhost:8080/services/note/1
```

## License

Copyright (C) 2012 Kevin Chard