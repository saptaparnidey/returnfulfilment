package com.gcp.returnfulfilment.publisher;

import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(defaultRequestChannel = "outputMessageChannel")
public interface PubsubOutboundGateway {
    void sendToPubsub(String text);
}
