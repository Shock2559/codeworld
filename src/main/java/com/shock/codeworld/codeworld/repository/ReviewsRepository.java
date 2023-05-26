package com.shock.codeworld.codeworld.repository;

import com.shock.codeworld.codeworld.entity.Products;
import com.shock.codeworld.codeworld.entity.Reviews;
import com.shock.codeworld.codeworld.entity.User;
import com.shock.codeworld.codeworld.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewsRepository extends CrudRepository<Reviews, Integer> {

}
