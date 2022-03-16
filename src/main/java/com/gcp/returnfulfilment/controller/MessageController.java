package com.gcp.returnfulfilment.controller;


import com.gcp.returnfulfilment.publisher.PubsubOutboundGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private PubsubOutboundGateway messagingGateway;

    @PostMapping("/publish")
    public ResponseEntity<String> publishMessage(@RequestBody String message) {
        messagingGateway.sendToPubsub(message);
        return new ResponseEntity<>("Message Successfully sent!", HttpStatus.OK);
    }
}
