package com.shock.codeworld.codeworld.repository;

import com.shock.codeworld.codeworld.entity.Role;
import com.shock.codeworld.codeworld.entity.User;
import com.shock.codeworld.codeworld.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
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

    @Query("UPDATE UserData c SET c.name = :name, c.email = :email, c.phone = :phone, c.dateBirth = :date_birth, " +
            "c.isValid = :is_valid where c.id = :id")
    void updateUser(int id, String name, String email, String phone, Date date_birth, boolean is_valid);

    Optional<UserData> findById(Integer id);

}
