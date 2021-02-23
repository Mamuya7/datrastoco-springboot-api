package com.mamuya.datrastocospringbootapi.dto;

import com.mamuya.datrastocospringbootapi.entities.Entreprise;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EntrepriseDTO implements DTOValidation<Entreprise> {

    private int id = -1;
    private String entname;
    private String entcode;
    private String entlocation;

    public EntrepriseDTO(Entreprise entreprise) {
        setId(entreprise.getId());
        setEntname(entreprise.getName());
        setEntcode(entreprise.getCode());
        setEntlocation(entreprise.getLocation());
    }

    @Override
    public boolean hasAllValidMappings() {
        return (
                hasValid(entname)
                && hasValid(entcode)
                && hasValid(entlocation)
        );
    }

    @Override
    public boolean hasAnyValidMappings() {
        return (
                hasValid(entname)
                || hasValid(entcode)
                || hasValid(entlocation)
        );
    }

    @Override
    public Entreprise createEntity() {

        Entreprise entreprise = new Entreprise();

        if(hasValid(id)){
            entreprise.setId(id);
        }

        entreprise.setName(entname);
        entreprise.setCode(entcode);
        entreprise.setLocation(entlocation);

        return entreprise;
    }

    @Override
    public Entreprise updateEntity(Entreprise entity) {

        if(hasValid(entname)){
            entity.setName(entname);
        }

        if(hasValid(entcode)){
            entity.setCode(entcode);
        }

        if(hasValid(entlocation)){
            entity.setLocation(entlocation);
        }

        return entity;
    }

    @Override
    public boolean hasValid(String item) {
        return (!(item == null) && !(item.trim().length() == 0));
    }

    @Override
    public boolean hasValid(Integer item) {
        return item >= 1;
    }
}
