package com.shock.codeworld.codeworld.controller.user;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserData {
    private int id;
    private String login;
    private String name;
    private String email;
    private String phone;
    private Date dateBirth;
    private boolean isValid;
}
