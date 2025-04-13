# Nuts Service Subscriber

A 3-layered Spring Boot application that listens to messages from RabbitMQ (`nutsQueue`), processes them, and persists them to a PostgreSQL database — fully Dockerized.


## Architecture Overview


+------------------+ +---------------+ +-----------------------+ | MessageSender | ─────▶ | RabbitMQ | ─────▶ | MessageListener | | (producer) | | (nutsQueue) | | MessageService | | | | | | PostgreSQL persistence| +------------------+ +---------------+ +-----------------------+
