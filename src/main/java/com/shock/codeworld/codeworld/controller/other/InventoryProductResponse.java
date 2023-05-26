package com.shock.codeworld.codeworld.controller.other;

import com.shock.codeworld.codeworld.entity.Products;
import com.shock.codeworld.codeworld.entity.StateProduct;
import com.shock.codeworld.codeworld.entity.StructuralDivision;
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
public class InventoryProductResponse {

    private String products;
    private String stateProduct;
    private Date dateCreate;
    private String structuralDivision;

}
