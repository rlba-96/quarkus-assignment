# Labseq Quarkus REST Service

This is a simple Quarkus-based REST API that calculates values from the **Labseq sequence**.

The Labseq sequence is defined as:

- `l(0) = 0`
- `l(1) = 1`
- `l(2) = 0`
- `l(3) = 1`
- `l(n) = l(n-4) + l(n-3)` for `n > 3`

The service exposes an endpoint to compute the value for any non-negative integer `n`.

## Tech Stack
- Java 17+
- Quarkus
- Maven
- RESTEasy Reactive
- OpenAPI
- JAX-RS

## Endpoints

**GET** `/labseq/{n}`

- Example:  
  `curl http://localhost:8080/labseq/15`  
  → `2`

---

## Running the application

### Option 1: dev mode

> Requires Java 17+

To run the application in dev mode, execute the following command:

```shell script
./mvnw quarkus:dev
```

Then open: http://localhost:8080/labseq/10

### Option 2: Docker container

Before building the container image run:

```shell script
./mvnw package
```

Then, build the image with:

```shell script
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/quarkus-assignment-jvm .
```

Then run the container using:

```shell script
docker run -i --rm -p 8080:8080 quarkus/quarkus-assignment-jvm
```

Then open: http://localhost:8080/labseq/10

## API Docs
After running the app, you can access Swagger UI for the OpenAPI docs on http://localhost:8080/q/swagger-ui/
