package com.shock.codeworld.codeworld.repository;

import com.shock.codeworld.codeworld.entity.StateProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateProductRepository extends CrudRepository<StateProduct, Integer> {
}
