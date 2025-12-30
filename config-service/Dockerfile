# Étape 1 : utiliser une image JDK officielle (Alpine légère)
FROM eclipse-temurin:21-jdk-alpine

# Étape 2 : créer le répertoire de travail dans le conteneur
WORKDIR /app



# Étape 3 : copier le JAR généré par Maven
COPY target/config-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
# Étape 4 : définir un volume temporaire (optionnel)
VOLUME /tmp

# Étape 5 : commande pour démarrer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
