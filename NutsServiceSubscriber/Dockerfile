# Use a base image with Java
FROM eclipse-temurin:17-jdk-alpine

# Install Maven
RUN apk add --no-cache maven

# Set working directory inside container
WORKDIR /app

# Copy all files from your project into the container
COPY . .

# Build the application (skip tests)
RUN mvn clean package -DskipTests

# Run the JAR file
CMD ["java", "-jar", "target/NutsServiceSubscriber-1.0-SNAPSHOT.jar"]
