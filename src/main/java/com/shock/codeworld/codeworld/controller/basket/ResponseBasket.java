package com.shock.codeworld.codeworld.controller.basket;

import com.shock.codeworld.codeworld.entity.StatusBasket;
import com.shock.codeworld.codeworld.entity.SubscriptionsBasket;
import com.shock.codeworld.codeworld.entity.UserData;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBasket {

    private int id;
    private Integer id_user;
    private Integer id_statusBasket;
    private Integer id_subscriptionsBasket;
    private String name_user;
    private String name_statusBasket;
    private String name_subscriptionsBasket;
    private Double cost;
}
