FROM openjdk:slim
ADD target/Kaddem-1.0.0.jar Kaddem
EXPOSE 8089:8089
ENTRYPOINT ["java","-jar","Kaddem"]