package com.shock.codeworld.codeworld.controller.basket;

import com.shock.codeworld.codeworld.entity.Basket;
import com.shock.codeworld.codeworld.entity.Products;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOrder {


    private int id;
    private int id_basket;
    private int id_product;
    private String name_product;
    private Date date_create;
    private Date delivery_date;


}
