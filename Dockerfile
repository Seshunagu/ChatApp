FROM openjdk:17-jdk-slim

WORKDIR /chat_App

# Copy the correct JAR file
COPY target/chatapp-0.0.1-SNAPSHOT.jar app.jar

# Run the application
ENTRYPOINT ["java","-jar","app.jar"]
