package com.mamuya.datrastocospringbootapi.service;

import com.mamuya.datrastocospringbootapi.entities.Role;

import java.util.List;

public interface RoleService {
    Role save(Role role);

    Role findById(int id);

    boolean existsById(int id);

    List<Role> findAll();

    long count();

    void deleteById(int id);
}
