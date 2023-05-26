package com.shock.codeworld.codeworld.controller.auth;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String login;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String role;
    private Date dateRegistration;
    private File photo;

}
