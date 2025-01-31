FROM openjdk:21-jdk-slim

WORKDIR /app

COPY envios.jar .

EXPOSE 7070

ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

CMD ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]