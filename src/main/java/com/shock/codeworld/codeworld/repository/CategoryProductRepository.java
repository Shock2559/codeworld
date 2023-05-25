package com.shock.codeworld.codeworld.repository;

import com.shock.codeworld.codeworld.entity.CategoryProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryProductRepository extends CrudRepository<CategoryProduct, Integer> {
}
