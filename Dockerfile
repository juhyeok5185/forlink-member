# 1. Use an OpenJDK base image
FROM openjdk:17-jdk-slim

# 2. Set the working directory inside the container
WORKDIR /app

# 3. Copy the JAR file into the container
COPY build/libs/*.jar app.jar

# 4. Expose port 10001 (컨테이너 내부 포트)
EXPOSE 10001

# 5. Run the application on port 10001
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=10001"]

#docker build -t forlink-member .
#docker run --name forlink-member -d -p 10001:10001 forlink-member
#docker network connect forlink-network forlink-member