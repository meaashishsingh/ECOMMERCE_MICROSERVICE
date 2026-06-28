package com.demo.producer;

import java.util.function.Supplier;

public class KafkaProducerStream {


    public Supplier<RiderLocation> sendRiderLocation(){
        return ()->{
            RiderLocation riderLocation=new RiderLocation("rider101", 28.6139, 77.2090);
            System.out.println("sending "+riderLocation.getRiderId());
            return  riderLocation;
        };
    }
}
