FROM openjdk:8
ADD target/returnfulfilment.jar returnfulfilment.jar
EXPOSE 8085
ENV GOOGLE_APPLICATION_CREDENTIALS returnfulfilmentpoc-5e9ab0f76361.json
ENV GOOGLE_CLOUD_PROJECT returnfulfilmentpoc
ENTRYPOINT ["java", "-jar", "returnfulfilment.jar"]