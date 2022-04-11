package com.gcp.returnfulfilment.controller;


import com.gcp.returnfulfilment.trigger.TriggerWorkflow;
import com.gcp.returnfulfilment.publisher.PublishCarrierPickupReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
public class MessageController {

//    @Autowired
//    private PubsubOutboundGateway messagingGateway;

    @Autowired
    private TriggerWorkflow triggerWorkflow;

    @Autowired
    private PublishCarrierPickupReq carrierPickupReq;
//
//    @PostMapping("/publish")
//    public ResponseEntity<String> sendMessage(@RequestBody String message) {
//        messagingGateway.sendToPubsub(message);
//        return new ResponseEntity<>("Message Successfully sent!", HttpStatus.OK);
//    }

    @PostMapping("/publish")
    public ResponseEntity<String> publishMessage(@RequestBody String message) throws Exception {
        String response = carrierPickupReq.publishMessage(message);
        return new ResponseEntity<>("Message Successfully sent with message id: "+response, HttpStatus.OK);
    }

    @PostMapping("/startevent")
    public ResponseEntity<String> startEvent(@RequestBody String payload)throws IOException, InterruptedException, ExecutionException {
        String resp=triggerWorkflow.triggerWorkflow();
        return new ResponseEntity<>("Workflow Event Started: "+resp, HttpStatus.OK);
    }
}
