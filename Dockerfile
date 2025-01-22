# 1. Use an OpenJDK base image
FROM openjdk:17-jdk-slim

# 2. Set the working directory inside the container
WORKDIR /app

# 3. Copy the JAR file into the container
COPY build/libs/*.jar app.jar

# 4. Expose port 10001
EXPOSE 10001

# 5. Run the application on port 10000
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=10000"]
