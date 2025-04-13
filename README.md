# Nuts Service Subscriber

A 3-layered Spring Boot application that listens to messages from RabbitMQ (`nutsQueue`), processes them, and persists them to a PostgreSQL database — fully Dockerized.


## Architecture Overview


+------------------+         +---------------+         +-----------------------+
|  MessageSender   | ─────▶  |  RabbitMQ     | ─────▶  |  MessageListener      |
|  (optional test) |         |  (nutsQueue)  |         |  MessageService       |
|                  |         |               |         |  (process & save)     |
+------------------+         +---------------+         +-----------------------+
                                                        |
                                                        ▼
                                               +-------------------+
                                               |   PostgreSQL DB   |
                                               +-------------------+


### 1. Clean everything (optional)

```bash
docker compose down -v

##  Tech Stack

- Java 17 + Spring Boot
- RabbitMQ
- PostgreSQL
- Docker + Docker Compose
- Maven

---

## Getting Started (Docker Setup)

### 1.  Clean everything (optional)

```bash
docker compose down -v
```

### 2. Build and Run the System

```bash
docker compose up --build
```

This starts:
- `postgres`: PostgreSQL 16
- `rabbitmq`: with management UI on port `15672`
- `nuts_app`: Your Spring Boot application

---

## RabbitMQ Management UI

Access at: [http://localhost:15672](http://localhost:15672)  
Login: `guest` / `guest`

1. Navigate to **Queues → nutsQueue**
2. Scroll to **Publish message** section
3. Enter a JSON or plain text message
4. Click **Publish** → Your Spring Boot app will receive and process it

---

## How It Works

- Messages are received by `MessageListener.java` via `@RabbitListener("nutsQueue")`
- Messages are validated and processed in `MessageService.java`
- Messages are saved to PostgreSQL via `MessageEntity` + `MessageRepository`
- You can send a test message using `MessageSender` on startup

---

## Project Structure

```
src/
├── api/           # RabbitMQ Listener + optional Sender
├── service/       # Business logic (message processing)
├── repository/    # Entity + JPA Repository
├── config/        # RabbitMQ queue declaration
└── NutsSubscriberApplication.java
```

##  Environment Configuration

Set in `application.properties` and overridden via Docker Compose:

```properties
spring.datasource.url=${SPRING_DATASOURCE_URL:jdbc:postgresql://localhost:5432/nutsdb}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME:postgres}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD:098148675}
spring.rabbitmq.host=${SPRING_RABBITMQ_HOST:localhost}
spring.rabbitmq.username=${SPRING_RABBITMQ_USERNAME:guest}
spring.rabbitmq.password=${SPRING_RABBITMQ_PASSWORD:guest}
```

---

## Docker Compose Overview

**`docker-compose.yml`** launches all required services:

- `postgres` (port 5432)
- `rabbitmq` (ports 5672 and 15672)
- `app` (Spring Boot app on port 8080)

All services are connected in the same Docker network.

---

## Useful Commands

| Action            | Command                         |
|-------------------|----------------------------------|
| Start system      | `docker compose up --build`      |
| Stop & clean      | `docker compose down -v`         |
| Check containers  | `docker ps`                      |
| RabbitMQ UI       | `http://localhost:15672`         |

---

## Author & Submission

**Project:** Nuts Service Subscriber  
**Prepared for:** Software Engineering Course  
**Developed by:** Laura Baldryan


---

## 💬 Questions?

If you have any issues running the project, feel free to open an issue or comment in the GitHub repo.

