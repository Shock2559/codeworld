package com.shock.codeworld.codeworld.repository;

import com.shock.codeworld.codeworld.entity.CategoryProduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryProductRepository extends CrudRepository<CategoryProduct, Integer> {

    @Query("select c from CategoryProduct c")
    List<CategoryProduct> getAllCategoryProduct();

    @Query("select c from CategoryProduct c where c.categoryProduct.id = :id")
    List<CategoryProduct> getCategoryProductForParent(int id);

    @Query("select c from CategoryProduct c where c.id = 2")
    List<CategoryProduct> getDefCategory();

    @Query("select c from CategoryProduct c where c.id = :id")
    CategoryProduct my_getCategoryProductById(int id);


}
