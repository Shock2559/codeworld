package com.shock.codeworld.codeworld.controller.other;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllSubscriptionsBasketResponse {

    private Integer id;
    private String name;

}
