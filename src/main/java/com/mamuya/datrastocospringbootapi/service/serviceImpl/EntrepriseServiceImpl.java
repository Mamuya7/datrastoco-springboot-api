package com.mamuya.datrastocospringbootapi.service.serviceImpl;

import com.mamuya.datrastocospringbootapi.entities.Entreprise;
import com.mamuya.datrastocospringbootapi.repository.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntrepriseServiceImpl implements EntrepriseService {

    private EntrepriseRepository entrepriseRepository;

    @Autowired
    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    @Override
    public Entreprise save(Entreprise entreprise) {
        return entrepriseRepository.save(entreprise);
    }

    @Override
    public Entreprise findById(int id) {
        Optional<Entreprise> result = entrepriseRepository.findById(id);
        return result.orElse(null);
    }

    @Override
    public boolean existsById(int id) {
        return entrepriseRepository.existsById(id);
    }

    @Override
    public List<Entreprise> findAll() {
        return entrepriseRepository.findAll();
    }

    @Override
    public long count() {
        return entrepriseRepository.count();
    }

    @Override
    public void deleteById(int id) {
        entrepriseRepository.deleteById(id);
    }
}
