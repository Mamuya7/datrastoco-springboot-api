package com.mamuya.datrastocospringbootapi.service;

import com.mamuya.datrastocospringbootapi.entities.Permission;

import java.util.List;

public interface PermissionService {
    Permission save(Permission permission);

    Permission findById(int id);

    boolean existsById(int id);

    List<Permission> findAll();

    long count();

    void deleteById(int id);
}
