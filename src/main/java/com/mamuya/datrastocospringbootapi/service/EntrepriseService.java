package com.mamuya.datrastocospringbootapi.service;

import com.mamuya.datrastocospringbootapi.entities.Entreprise;

import java.util.List;

public interface EntrepriseService {
    Entreprise save(Entreprise entreprise);

    Entreprise findById(int id);

    boolean existsById(int id);

    List<Entreprise> findAll();

    long count();

    void deleteById(int id);
}
