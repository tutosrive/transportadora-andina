# Fase 1: Construcción con Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copiar el pom.xml primero para que las dependencias se cacheen
COPY pom.xml .

# Descargar dependencias antes de copiar el código fuente completo
RUN --mount=type=cache,target=/root/.m2 mvn dependency:go-offline

# Copiar todo el código fuente después de descargar dependencias
COPY . .

# Compilar el proyecto sin ejecutar pruebas
RUN --mount=type=cache,target=/root/.m2 mvn -B -DskipTests clean package

# Fase 2: Imagen final con OpenJDK
FROM openjdk:21-jdk-slim

WORKDIR /app

# Copiamos el JAR desde la fase de compilación
COPY --from=build /app/target/*.jar app.jar

# Configuramos el puerto dinámico que Railway asigna
ENV PORT=${PORT}

# Exponer el puerto (Railway asignará el suyo dinámicamente)
EXPOSE 7070

# Comando de ejecución con puerto dinámico
CMD ["sh", "-c", "java -jar app.jar --server.port=${PORT:-7070}"]
