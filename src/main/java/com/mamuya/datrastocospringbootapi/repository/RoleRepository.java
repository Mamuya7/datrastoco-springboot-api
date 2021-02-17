package com.mamuya.datrastocospringbootapi.repository;

import com.mamuya.datrastocospringbootapi.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}


