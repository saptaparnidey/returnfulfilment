package com.gcp.returnfulfilment.publisher;

import com.google.api.core.ApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.outbound.PubSubMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class PublishCarrierPickupReq {

    Log log = LogFactory.getLog(PublishCarrierPickupReq.class);

    @Bean
    @ServiceActivator(inputChannel = "outputMessageChannel")
    public MessageHandler messageSender(PubSubTemplate pubsubTemplate) {
        return new PubSubMessageHandler(pubsubTemplate, "t.publishcarrierupdate");
    }

    public String publishMessage(String message) throws Exception {
        String projectName = "returnfulfilment-demo";
        String topicName = "t.test_topic";

        ProjectTopicName topic = ProjectTopicName.of(projectName,topicName);

        Publisher publisher =null;
        try {
            publisher = Publisher.newBuilder(topic).build();

            ByteString data = ByteString.copyFromUtf8(message);

            PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();

            ApiFuture<String> messageIdFuture = publisher.publish(pubsubMessage);
            String messageId = messageIdFuture.get();
            log.info("Published message ID: " + messageId);
            return messageId;
        }
        finally {
            if(publisher != null){
                publisher.shutdown();
                publisher.awaitTermination(1, TimeUnit.MINUTES);
            }
        }

    }
}
