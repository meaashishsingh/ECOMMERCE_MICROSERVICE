package com.demo.consumer;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.function.Consumer;

@Configuration
public class KafkaConsumerNew {

    @Bean
    public Consumer<RiderLocation> processRiderLocation() {


        return location -> {
            System.out.println("Recived  " + location.getRiderId() + " @ " + location.getLatitude() + " @ " + location.getLongitude());
        };
    }

    @Bean
    public Consumer<String> processRiderStatus() {


        return status -> {
            System.out.println("Recived  " + status);
        };
    }
}
