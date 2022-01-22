package com.inzynieria.wisielec.repository;

import com.inzynieria.wisielec.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    @Query("select u from User u where u.username = :username and u.password = :password")
    public User login(@Param("username") String username, @Param("password") String password);
}
