package com.shock.codeworld.codeworld.repository;

import com.shock.codeworld.codeworld.entity.Products;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends CrudRepository<Products, Integer> {
}
