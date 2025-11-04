# ED-159-AWS

FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/*.jar app.jar

# TODO change to prod later
ENV SPRING_PROFILES_ACTIVE=dev

EXPOSE 8585
ENTRYPOINT ["java","-jar","app.jar"]