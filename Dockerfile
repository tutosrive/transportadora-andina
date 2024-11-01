# Usa una imagen oficial de Java
FROM openjdk:21-jdk

# Copia el archivo JAR en el contenedor
COPY envios.jar /app/envios.jar

# Define el directorio de trabajo
WORKDIR /app

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "envios.jar"]
