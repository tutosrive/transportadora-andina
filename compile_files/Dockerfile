# Fase 1: Construcción con Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Configuramos el directorio de trabajo
WORKDIR /app

# Copiamos solo el archivo pom.xml primero (para optimizar caché de dependencias)
COPY pom.xml .

# Descargamos dependencias antes de copiar el código fuente completo
RUN --mount=type=cache,target=/root/.m2 mvn dependency:go-offline

# Copiamos el código fuente
COPY . .

# Compilamos el proyecto sin ejecutar pruebas
RUN --mount=type=cache,target=/root/.m2 mvn -B -DskipTests clean package

# Fase 2: Imagen ligera con JDK solo para ejecución
FROM openjdk:21-jdk-slim

# Configuramos el directorio de trabajo
WORKDIR /app

# Copiamos el JAR desde la fase de compilación
COPY --from=build /app/target/envios.jar app.jar

# Exponemos el puerto (pero usamos el que Railway asigne)
EXPOSE 7070

# Definimos variable para manejar el puerto de Railway
ENV PORT=7070

# Comando de ejecución con puerto dinámico
CMD ["sh", "-c", "java -jar app.jar --server.port=${PORT}"]
