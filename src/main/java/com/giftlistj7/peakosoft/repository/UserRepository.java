package com.giftlistj7.peakosoft.repository;

import com.giftlistj7.peakosoft.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("select user from User user where user.email=:email")
   Optional<User> findByEmail(@Param("email") String email);

}
