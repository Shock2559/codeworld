package com.shock.codeworld.codeworld.repository;

import com.shock.codeworld.codeworld.entity.CategoryProduct;
import com.shock.codeworld.codeworld.entity.StatusBasket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllStatusBasketRepository extends CrudRepository<StatusBasket, Integer> {

    @Query("select c from StatusBasket c")
    List<StatusBasket> getAllStatusBasket();

}
