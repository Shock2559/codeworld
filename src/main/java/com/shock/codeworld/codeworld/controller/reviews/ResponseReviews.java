package com.shock.codeworld.codeworld.controller.reviews;

import com.shock.codeworld.codeworld.entity.UserData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseReviews {

    private int id;
    private int user;
    private int farmer;
    private String nameUser;
    private String reviews;
    private int assessment;

}
