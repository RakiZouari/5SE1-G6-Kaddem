# Utilisez l'image OpenJDK pour construire votre application Spring
FROM openjdk:11

# Copiez le jar de l'application Spring dans le conteneur
COPY target/Kaddem-1.2.jar .

# Exposez le port sur lequel votre application Spring s'exécute (par défaut 8080)
EXPOSE 8089

# Commande pour lancer l'application Spring
CMD ["java", "-jar", "Kaddem-1.2.jar"]
