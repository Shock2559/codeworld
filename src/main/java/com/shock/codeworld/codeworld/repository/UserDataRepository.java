package com.shock.codeworld.codeworld.repository;

import com.shock.codeworld.codeworld.entity.Role;
import com.shock.codeworld.codeworld.entity.User;
import com.shock.codeworld.codeworld.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Integer> {


    @Query("select c from UserData c where c.user = :user")
    UserData getUserDataForUser(User user);

    @Query("select c from UserData c where c.user.role = :role")
    List<UserData> getUserDataForRole(Role role);

    @Query("select c from UserData c where c.user.role <> :role")
    List<UserData> getUserDataForAllRole(Role role);

}
