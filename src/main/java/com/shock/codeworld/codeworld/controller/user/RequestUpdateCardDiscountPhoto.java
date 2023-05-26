package com.shock.codeworld.codeworld.controller.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdateCardDiscountPhoto {
    private Integer id;
    private String card;
    private Integer discount;
    //private File Photo;
    private String Photo;

}
