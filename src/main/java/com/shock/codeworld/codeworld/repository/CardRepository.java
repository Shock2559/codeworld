package com.shock.codeworld.codeworld.repository;

import com.shock.codeworld.codeworld.entity.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<Card, Integer> {



}
