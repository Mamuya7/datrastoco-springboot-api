package com.mamuya.datrastocospringbootapi.dto;

import com.mamuya.datrastocospringbootapi.entities.Permission;

public class PermissionDTO implements DTOValidation<Permission> {

    public PermissionDTO(Permission permission) {
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
    public Permission createEntity() {
        return null;
    }

    @Override
    public Permission updateEntity(Permission entity) {
        return null;
    }

    @Override
    public boolean hasValid(String item) {
        return false;
    }
}
