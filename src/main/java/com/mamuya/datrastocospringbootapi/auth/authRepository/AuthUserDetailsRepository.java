package com.mamuya.datrastocospringbootapi.auth.authRepository;

import com.mamuya.datrastocospringbootapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthUserDetailsRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.userName = ?1")
    Optional<User> findByUsername(String username);
}
