# Establecer la imagen base de Java 17 y Maven
FROM openjdk:17-jdk-slim AS build

# Instalar Maven
RUN apt-get update && apt-get install -y maven

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo pom.xml y descargar las dependencias de Maven
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar el código fuente y compilar la aplicación
COPY src ./src
RUN mvn package -DskipTests

# Crear una nueva imagen utilizando una imagen base Tomcat
FROM tomcat:latest

# Copiar el archivo WAR generado en la imagen de Tomcat
COPY --from=build /app/target/reciclarg.war /usr/local/tomcat/webapps/

# Exponer el puerto 8080 (puerto predeterminado de Tomcat)
EXPOSE 8080

# Iniciar Tomcat al ejecutar el contenedor
CMD ["catalina.sh", "run"]
