package com.shock.codeworld.codeworld.repository;

import com.shock.codeworld.codeworld.entity.Basket;
import com.shock.codeworld.codeworld.entity.OrderBasket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderBasketRepository extends CrudRepository<OrderBasket, Integer> {

    @Query("select c from OrderBasket c where c.basket.id = :id")
    List<OrderBasket> my_getOrderByIdBasket(Integer id);

    @Query("select c from OrderBasket c where c.id = :id")
    OrderBasket my_getOrderById(Integer id);

}