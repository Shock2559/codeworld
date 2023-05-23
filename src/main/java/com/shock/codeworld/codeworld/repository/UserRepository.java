package com.shock.codeworld.codeworld.repository;

import com.shock.codeworld.codeworld.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByLogin(String login);

    @Query("select c from User c where c.login = :login")
    List<User> findListByLogin(String login);
}
