# Utiliser une image Java de base
FROM openjdk:17-jdk-slim

# Ajouter le fichier JAR de l'application
#COPY target/springbatch-0.0.1-SNAPSHOT.jar app.jar

# Exposer le port
EXPOSE 8080

# Démarrer l'application
#ENTRYPOINT ["java", "-jar", "/app.jar"]
