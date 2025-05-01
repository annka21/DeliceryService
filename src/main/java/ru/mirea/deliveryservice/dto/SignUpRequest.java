package ru.mirea.deliveryservice.dto;

import lombok.Data;

@Data
public class SignUpRequest {

    private String email;
    private String password;
    private String firstName;
    private String secondName;
    private String telephoneNumber;
    private String address;
}