FROM openjdk:8
ADD target/returnfulfilment.jar returnfulfilment.jar
ADD returnfulfilmentpoc-5e9ab0f76361.json returnfulfilmentpoc-5e9ab0f76361.json
#EXPOSE 9095
#ENV PORT 9095
ENV GOOGLE_APPLICATION_CREDENTIALS returnfulfilmentpoc-5e9ab0f76361.json
ENV GOOGLE_CLOUD_PROJECT returnfulfilmentpoc
ENTRYPOINT ["java", "-jar", "returnfulfilment.jar"]