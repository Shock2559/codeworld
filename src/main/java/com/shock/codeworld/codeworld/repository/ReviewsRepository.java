package com.shock.codeworld.codeworld.repository;

import com.shock.codeworld.codeworld.entity.Products;
import com.shock.codeworld.codeworld.entity.Reviews;
import com.shock.codeworld.codeworld.entity.User;
import com.shock.codeworld.codeworld.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsRepository extends CrudRepository<Reviews, Integer> {

    @Query("select c from Reviews c where c.farmer.id = :id")
    List<Reviews> my_getReviewsByIdFarmer(Integer id);

    @Query("select c from Reviews c")
    List<Reviews> my_getAllReviews();

}
