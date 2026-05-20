package com.ecommerce.user.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "address")
public class Address {

    @Id
    private String id;

    private String street;
    private String city;
    private String state;
    private String country;
    private String zipcode;
}