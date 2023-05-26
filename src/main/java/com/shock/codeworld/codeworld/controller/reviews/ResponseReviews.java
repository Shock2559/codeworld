package com.shock.codeworld.codeworld.controller.reviews;

import com.shock.codeworld.codeworld.entity.UserData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseReviews {

    private int id;
    private UserData user;
    private UserData farmer;
    private String reviews;
    private int assessment;

}
