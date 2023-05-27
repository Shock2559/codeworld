package com.shock.codeworld.codeworld.repository;

import com.shock.codeworld.codeworld.controller.product.ResponseProduct;
import com.shock.codeworld.codeworld.entity.CategoryProduct;
import com.shock.codeworld.codeworld.entity.InventoryProduct;
import com.shock.codeworld.codeworld.entity.Products;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends CrudRepository<Products, Integer> {

    @Query("select c from Products c where c.userData.id = :id")
    List<Products> getAllProducts(int id);

    @Query("select c from Products c")
    List<Products> my_getAllProduct();

    @Query("select c from Products c where c.id = :id")
    Products my_getProductById(Integer id);

    @Query("select c from Products c where c.userData.id = :id and c.categoryProduct = :category")
    List<Products> my_getAllProductByCategory(Integer id, CategoryProduct category);

}
