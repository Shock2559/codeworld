package com.shock.codeworld.codeworld.controller.other;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllStatusBasketResponse {

    private Integer id;
    private String name;

}
