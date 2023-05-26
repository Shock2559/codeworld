package com.shock.codeworld.codeworld.repository;

import com.shock.codeworld.codeworld.entity.StatusBasket;
import com.shock.codeworld.codeworld.entity.SubscriptionsBasket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllSubscriptionsBasketRepository extends CrudRepository<SubscriptionsBasket, Integer> {

    @Query("select c from SubscriptionsBasket c")
    List<SubscriptionsBasket> getAllSubscriptionsBasket();

    @Query("select c from SubscriptionsBasket c where c.id = :id")
    SubscriptionsBasket getSubscriptionsBasketById(Integer id);

}
