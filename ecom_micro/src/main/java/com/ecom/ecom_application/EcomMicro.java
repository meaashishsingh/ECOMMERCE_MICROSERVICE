package com.ecom.ecom_application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class EcomMicro {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
		System.out.println("JVM Timezone: " + java.util.TimeZone.getDefault());
		SpringApplication.run(EcomMicro.class, args);
	}

}
