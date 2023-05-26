package com.shock.codeworld.codeworld.repository;

import com.shock.codeworld.codeworld.entity.InventoryProduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryProductRepository extends CrudRepository<InventoryProduct, Integer> {

    @Query("select c from InventoryProduct c where c.products.id = :id")
    List<InventoryProduct> getInventoryProductForIdProduct(int id);

}
