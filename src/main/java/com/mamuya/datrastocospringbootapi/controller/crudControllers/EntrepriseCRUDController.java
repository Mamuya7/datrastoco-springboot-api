package com.mamuya.datrastocospringbootapi.controller.crudControllers;

import com.mamuya.datrastocospringbootapi.controller.CRUDController;
import com.mamuya.datrastocospringbootapi.dto.EntrepriseDTO;
import com.mamuya.datrastocospringbootapi.entities.Entreprise;
import com.mamuya.datrastocospringbootapi.service.EntrepriseService;
import com.mamuya.datrastocospringbootapi.utility.Response;
import com.mamuya.datrastocospringbootapi.utility.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/entreprise")
public class EntrepriseCRUDController implements CRUDController<EntrepriseDTO>, ServerResponse {

    private EntrepriseService entrepriseService;

    @Autowired
    public EntrepriseCRUDController(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }

    @PostMapping
    @Override
    public ResponseEntity<Response> create(@RequestBody EntrepriseDTO request) {

        if(!request.hasAllValidMappings()){
            return new ResponseEntity<>(ObjectRequestMismatch, HttpStatus.CONFLICT);
        }

        Entreprise entreprise = request.createEntity();

        entreprise = entrepriseService.save(entreprise);

        SuccessfulRequestExecution.setData(entreprise);

        return new ResponseEntity<>(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Response> readOne(@PathVariable Integer id) {
        Entreprise entreprise = entrepriseService.findById(id);

        if(entreprise == null){
            return new ResponseEntity<>(ObjectForIdentityNotFound, HttpStatus.NOT_FOUND);
        }

        EntrepriseDTO entrepriseDTO = new EntrepriseDTO(entreprise);
        SuccessfulRequestExecution.setData(entrepriseDTO);

        return new ResponseEntity<>(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @GetMapping
    @Override
    public ResponseEntity<Response> readAll() {

        List<Entreprise> entreprises = entrepriseService.findAll();
        List<EntrepriseDTO> dtos = entreprises.stream()
                                        .map(entreprise -> new EntrepriseDTO(entreprise))
                                    .collect(Collectors.toList());

        SuccessfulRequestExecution.setData(dtos);
        return new ResponseEntity<>(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<Response> update(@PathVariable Integer id, @RequestBody EntrepriseDTO request) {
        Entreprise entreprise = entrepriseService.findById(id);

        if(entreprise == null){
            return new ResponseEntity<>(ObjectForIdentityNotFound, HttpStatus.NOT_FOUND);
        }

        if(!request.hasAnyValidMappings()){
            return new ResponseEntity<>(ObjectRequestMismatch, HttpStatus.CONFLICT);
        }

        request.updateEntity(entreprise);

        entreprise = entrepriseService.save(entreprise);

        SuccessfulRequestExecution.setData(new EntrepriseDTO(entreprise));

        return new ResponseEntity<>(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Response> delete(@PathVariable Integer id) {
        Entreprise entreprise = entrepriseService.findById(id);

        if(entreprise == null){
            return new ResponseEntity<>(ObjectForIdentityNotFound, HttpStatus.NOT_FOUND);
        }

        entrepriseService.deleteById(id);
        SuccessfulRequestExecution.setData(null);

        return new ResponseEntity<>(SuccessfulRequestExecution, HttpStatus.OK);
    }
}
