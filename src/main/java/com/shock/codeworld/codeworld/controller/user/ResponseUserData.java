package com.shock.codeworld.codeworld.controller.user;

import com.shock.codeworld.codeworld.entity.Role;
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
public class ResponseUserData {
    private int id;
    private String login;
    private String name;
    private String email;
    private String phone;
    private String address;
    private Date dateRegistration;
    private String photo;
    private Role role;
    private Integer discount;
    private String card;
    private Integer id_basket;
}
