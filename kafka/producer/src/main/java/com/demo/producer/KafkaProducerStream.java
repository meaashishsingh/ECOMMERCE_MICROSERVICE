package com.demo.producer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;

import java.util.Random;
import java.util.function.Supplier;
@Configuration
public class KafkaProducerStream {

  @Bean
    public Supplier<RiderLocation> sendRiderLocation(){
      Random random=new Random();
        return ()->{
            String riderId="rider"+random.nextInt(20);
            RiderLocation riderLocation=new RiderLocation(riderId, 28.6139, 77.2090);
            System.out.println("sending "+riderLocation.getRiderId());
            return  riderLocation;
        };
    }

    @Bean
    public Supplier<Message<String>> sendRiderStatus(){
        Random random=new Random();
        return ()->{
            String riderId="rider"+random.nextInt(20);
            String Status=random.nextBoolean()?"rider started ":"rider completed";

            System.out.println("sending  "+Status);
            return MessageBuilder.withPayload(riderId+" : "+Status).setHeader(KafkaHeaders.KEY,riderId.getBytes()).setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.TEXT_PLAIN).build();
        };
    }
}
