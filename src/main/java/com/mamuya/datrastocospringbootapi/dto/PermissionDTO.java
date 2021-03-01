package com.mamuya.datrastocospringbootapi.dto;

import com.mamuya.datrastocospringbootapi.entities.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PermissionDTO implements DTOValidation<Permission> {

    private int id = -1;
    private String entity;
    private String type;

    public PermissionDTO(Permission permission) {
        setId(permission.getId());
        setEntity(permission.getEntity());
        setType(permission.getType());
    }

    @Override
    public boolean hasAllValidMappings() {
        return (hasValid(entity) && hasValid(type));
    }

    @Override
    public boolean hasAnyValidMappings() {
        return (hasValid(entity) || hasValid(type));
    }

    @Override
    public Permission createEntity() {

        Permission permission = new Permission();

        if(hasValid(id)){
            permission.setId(id);
        }

        permission.setEntity(entity);
        permission.setType(type);

        return permission;
    }

    @Override
    public Permission updateEntity(Permission permission) {

        if(hasValid(entity)){
            permission.setEntity(entity);
        }

        if(hasValid(type)){
            permission.setType(type);
        }

        return permission;
    }

    @Override
    public boolean hasValid(String item) {
        return ((item != null) && (item.trim().length() > 0));
    }

    @Override
    public boolean hasValid(Integer item) {
        return item > 0;
    }
}
