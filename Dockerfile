WORKDIR /app
COPY backend/ .
RUN chmod +x ../gradlew && ../gradlew :backend:build
CMD ["java", "-jar", "build/libs/backend-0.0.1-SNAPSHOT.jar"]
