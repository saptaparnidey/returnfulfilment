FROM openjdk:8
ADD target/returnfulfilment.jar returnfulfilment.jar
#EXPOSE 9095
#ENV PORT 9095
ENV GOOGLE_CLOUD_PROJECT returnfulfilmentpoc
ENTRYPOINT ["java", "-jar", "returnfulfilment.jar"]