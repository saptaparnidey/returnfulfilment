package com.gcp.returnfulfilment.consumer;

import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.pubsub.v1.PubsubMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gcp.pubsub.support.BasicAcknowledgeablePubsubMessage;
import org.springframework.cloud.gcp.pubsub.support.GcpPubSubHeaders;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class CarrierPickupUpdateReceiver {

    private Log log = LogFactory.getLog(CarrierPickupUpdateReceiver.class);

    // Define what happens to the messages arriving in the message channel.
    @ServiceActivator(inputChannel = "inputMessageChannel")
    public void messageReceiver(
            String payload,
            @Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) BasicAcknowledgeablePubsubMessage message) {
        log.info("Message arrived via an inbound channel adapter from s.carrierupdate! Payload: " + payload);
        message.ack();
    }
}
