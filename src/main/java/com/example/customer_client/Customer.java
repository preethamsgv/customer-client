package com.example.customer_client;


import lombok.Data;

import java.util.UUID;
@Data
public class Customer {

    private UUID id;

    private String firstName;

    private String middleName;

    private String lastName;

    private String emailAddress;

    private String phoneNumber;
}


