FROM openjdk:17

ARG APP_JAR=*.jar

COPY ${APP_JAR} FindNumber.jar

ENTRYPOINT ["java", "-jar", "FindNumber.jar"]

EXPOSE 8080