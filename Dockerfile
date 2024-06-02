# Use the official OpenJDK 11 image as the base image
FROM adoptopenjdk/openjdk11:alpine-jre

# Set the working directory in the container
WORKDIR /app

# Copy the Quarkus application JAR file and its dependencies into the container
COPY target/quarkusSakila-1.0.1-SNAPSHOT.jar /app/app.jar
COPY target/quarkus-app /app/quarkus-app

# Expose the port the Quarkus application runs on
EXPOSE 8080

# Define the command to run the Quarkus application when the container starts
CMD ["java", "-jar", "/app/app.jar"]