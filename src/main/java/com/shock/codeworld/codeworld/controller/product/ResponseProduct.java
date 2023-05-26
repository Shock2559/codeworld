package com.shock.codeworld.codeworld.controller.product;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProduct {
    private Integer id;
    private int id_user;
    private int id_category;
    private String categoryName;
    private String name;
    private String description;
    private Double cost;
    private File photo;

}
