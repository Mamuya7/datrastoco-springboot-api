package com.mamuya.datrastocospringbootapi.dto;

import com.mamuya.datrastocospringbootapi.entities.Entreprise;

public class EntrepriseDTO implements DTOValidation<Entreprise> {

    public EntrepriseDTO(Entreprise entreprise) {
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
    public Entreprise createEntity() {
        return null;
    }

    @Override
    public Entreprise updateEntity(Entreprise entity) {
        return null;
    }

    @Override
    public boolean hasValid(String item) {
        return false;
    }
}
