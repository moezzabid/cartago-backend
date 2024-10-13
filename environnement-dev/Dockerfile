# Étape 1: Utiliser une image Maven pour la compilation
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app

# Copier le pom.xml et télécharger les dépendances Maven
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copier tout le code source et compiler le projet
COPY src ./src
RUN mvn clean package -DskipTests

# Étape 2: Utiliser une image Java légère pour l'exécution
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copier l'artefact construit depuis l'étape de compilation
COPY --from=build /app/target/apiCartago.jar /app/apiCartago.jar

# Exposer le port 8080 pour l'application Spring Boot
EXPOSE 8082

# Commande pour démarrer l'application
ENTRYPOINT ["java", "-jar", "/app/apiCartago.jar"]
