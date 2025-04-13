# Nuts Service Subscriber - Dockerized Version

This project is a 3-layered Java application that connects to a RabbitMQ message bus (nutsQueue), processes incoming messages, and stores them in a PostgreSQL database.

It includes:

📨 RabbitMQ Listener

🔁 Business Logic Processor

🗄️ PostgreSQL Persistence via Spring Data JPA

This version is Dockerized. All services (app, RabbitMQ, PostgreSQL) are started using Docker Compose.


## 📁 Project Structure
```
src/
  main/
    java/
      org.example.nutssubscriber/
        api/                <-- RabbitMQ message listener
        config/             <-- RabbitMQ configuration class
        service/            <-- Business logic
        repository/         <-- Data access layer
        NutsSubscriberApplication.java
  test/
    java/
      org.example.nutssubscriber/
        repository/         <-- JPA repository test
        service/            <-- Service logic test
```

## Prerequisites

- Java 17 or later
- Maven
- Docker + Docker Compose

---

## Configuration

### `application.properties`
Located at: `src/main/resources/application.properties`

```properties
# PostgreSQL Connection (Docker overrides this)
spring.datasource.url=${SPRING_DATASOURCE_URL:jdbc:postgresql://localhost:5432/nutsdb}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME:postgres}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD:password}

# JPA & Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# RabbitMQ Connection (Docker overrides this)
spring.rabbitmq.host=${SPRING_RABBITMQ_HOST:rabbitmq}
spring.rabbitmq.username=${SPRING_RABBITMQ_USERNAME:guest}
spring.rabbitmq.password=${SPRING_RABBITMQ_PASSWORD:guest}
```

## 🐳 Dockerized Setup

### `docker-compose.yml`
```yaml
version: '3.8'

services:
  postgres:
    image: postgres:16
    container_name: nuts_postgres
    environment:
      POSTGRES_DB: nutsdb
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: 098148675
    ports:
      - "5432:5432"

  rabbitmq:
    image: rabbitmq:3-management
    container_name: nuts_rabbitmq
    environment:
      RABBITMQ_DEFAULT_USER: guest
      RABBITMQ_DEFAULT_PASS: guest
    ports:
      - "5672:5672"
      - "15672:15672"

  app:
    build: .
    container_name: nuts_app
    depends_on:
      - postgres
      - rabbitmq
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/nutsdb
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: password
      SPRING_RABBITMQ_HOST: rabbitmq
      SPRING_RABBITMQ_USERNAME: guest
      SPRING_RABBITMQ_PASSWORD: guest
    ports:
      - "8080:8080"
```

## Run the System
From the root of your project:

```bash
docker-compose up --build
```

This will:
- Start RabbitMQ on port 5672 and its UI at 15672
- Start PostgreSQL on port 5432
- Build and run your Spring Boot application

---

## Testing It End-to-End

1. Access the RabbitMQ Management UI:
   - [http://localhost:15672](http://localhost:15672)
   - Login: guest / guest

2. Go to **Queues** tab → click `nutsQueue`

3. Scroll to **Publish message**

4. Write a test message like:
```json
"Hello from RabbitMQ via Docker!"
```
5. Click **Publish Message**

You should see in the logs:
```
Received message from RabbitMQ: Hello from RabbitMQ via Docker!
Message saved to database.
```

6. To verify in PostgreSQL:
```bash
docker exec -it nuts_postgres psql -U postgres -d nutsdb
```

Then run:
```sql
SELECT * FROM message_entity;
```

You should see the table's content.

---




## Architecture Overview

```
+------------------+       +---------------+       +------------------------+
| MessageSender    | --->  | RabbitMQ      | --->  | MessageListener       |
| (producer - UI)  |       | (nutsQueue)   |       | MessageService        |
|                  |       |               |       | PostgreSQL persistence|
+------------------+       +---------------+       +------------------------+
```


## Author & Submission

**Project:** Nuts Service Subscriber - Dockerized version  
**Prepared for:** Software Engineering Course  
**Developed by:** Laura Baldryan


---

## 💬 Questions?

If you have any issues running the project, feel free to open an issue or comment in the GitHub repo.

