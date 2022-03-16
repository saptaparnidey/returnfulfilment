package com.gcp.returnfulfilment.publisher;

import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.outbound.PubSubMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;

@Component
public class PublishCarrierPickupReq {

    @Bean
    @ServiceActivator(inputChannel = "outputMessageChannel")
    public MessageHandler messageSender(PubSubTemplate pubsubTemplate) {
        return new PubSubMessageHandler(pubsubTemplate, "t.publishcarrierupdate");
    }
}
