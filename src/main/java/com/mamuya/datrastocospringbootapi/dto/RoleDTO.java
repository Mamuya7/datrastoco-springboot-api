package com.mamuya.datrastocospringbootapi.dto;

import com.mamuya.datrastocospringbootapi.entities.Role;

public class RoleDTO implements DTOValidation<Role> {

    public RoleDTO(Role role) {
    }

    @Override
    public boolean hasAllValidMappings() {
        return false;
    }

    @Override
    public boolean hasAnyValidMappings() {
        return false;
    }

    @Override
    public Role createEntity() {
        return null;
    }

    @Override
    public Role updateEntity(Role entity) {
        return null;
    }

    @Override
    public boolean hasValid(String item) {
        return false;
    }
}
