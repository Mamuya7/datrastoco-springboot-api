package com.mamuya.datrastocospringbootapi.service;

import com.mamuya.datrastocospringbootapi.entities.User;

import java.util.List;

public interface UserService {
    User save(User user);

    User findById(int id);

    boolean existsById(int id);

    List<User> findAll();

    long count();

    void deleteById(int id);
}
