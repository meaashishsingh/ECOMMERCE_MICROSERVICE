package com.ecom.ecom_application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDto {


    private String street;
    private String city;
    private String state;
    private String country;
    private String zipcode;
}
