package com.mamuya.datrastocospringbootapi.repository;

import com.mamuya.datrastocospringbootapi.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {
}
