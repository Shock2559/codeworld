package com.shock.codeworld.codeworld.repository;

import com.shock.codeworld.codeworld.entity.Basket;
import com.shock.codeworld.codeworld.entity.CategoryProduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketRepository extends CrudRepository<Basket, Integer> {

    @Query("select c from Basket c where c.user.id = :id")
    List<Basket> my_getBasketByIdUser(Integer id);

    @Query("select c from Basket c where c.id = :id")
    Basket my_getBasketById(Integer id);

}
