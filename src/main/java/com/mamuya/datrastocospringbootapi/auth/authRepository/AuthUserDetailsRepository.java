package com.mamuya.datrastocospringbootapi.auth.authRepository;

import com.mamuya.datrastocospringbootapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthUserDetailsRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.userName = ?1")
    User findByUsername(String username);
}
