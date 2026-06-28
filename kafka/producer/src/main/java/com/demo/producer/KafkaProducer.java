package com.demo.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class KafkaProducer {

//    private final KafkaTemplate<String, String> kafkaTemplate;
private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String message) {
        RiderLocation riderlocation = new RiderLocation("rider101", 28.6139, 77.2090);

//        kafkaTemplate.send("my-topic", message);
        kafkaTemplate.send("my-new-topic-2", riderlocation);

        return "Message sent: " +riderlocation.getRiderId();
    }
}