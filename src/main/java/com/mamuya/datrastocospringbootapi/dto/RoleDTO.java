package com.mamuya.datrastocospringbootapi.dto;

import com.mamuya.datrastocospringbootapi.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoleDTO implements DTOValidation<Role> {

    private int id = -1;
    private String rname;
    private String rcode;
    private String rtype;

    public RoleDTO(Role role) {
        setId(role.getId());
        setRname(role.getName());
        setRcode(role.getCode());
        setRtype(role.getType());
    }

    @Override
    public boolean hasAllValidMappings() {

        return (
                hasValid(rname)
                && hasValid(rcode)
                && hasValid(rtype)
                );
    }

    @Override
    public boolean hasAnyValidMappings() {

        return (
                hasValid(rname)
                || hasValid(rcode)
                || hasValid(rtype)
                );
    }

    @Override
    public Role createEntity() {

        Role role = new Role();

        if(hasValid(id)){
            role.setId(id);
        }

        role.setName(rname);
        role.setCode(rcode);
        role.setType(rtype);

        return role;
    }

    @Override
    public Role updateEntity(Role entity) {

        if(hasValid(rname)){
            entity.setName(rname);
        }

        if(hasValid(rcode)){
            entity.setCode(rcode);
        }

        if(hasValid(rtype)){
            entity.setType(rtype);
        }

        return entity;
    }

    @Override
    public boolean hasValid(String item) {

        return ((item != null) && (item.trim().length() > 0));
    }

    @Override
    public boolean hasValid(Integer item) {
        return item >= 1;
    }
}
