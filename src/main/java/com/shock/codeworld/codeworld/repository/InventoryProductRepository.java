package com.shock.codeworld.codeworld.repository;

import com.shock.codeworld.codeworld.entity.InventoryProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryProductRepository extends CrudRepository<InventoryProduct, Integer> {
}
