package com.mamuya.datrastocospringbootapi.controller.crudControllers;

import com.mamuya.datrastocospringbootapi.controller.CRUDController;
import com.mamuya.datrastocospringbootapi.dto.SaleDTO;
import com.mamuya.datrastocospringbootapi.entities.Sale;
import com.mamuya.datrastocospringbootapi.service.SaleService;
import com.mamuya.datrastocospringbootapi.utility.Response;
import com.mamuya.datrastocospringbootapi.utility.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sale")
public class SaleCRUDController implements CRUDController<SaleDTO>, ServerResponse {

    private SaleService saleService;

    @Autowired
    public SaleCRUDController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    @Override
    public ResponseEntity<Response> create(@RequestBody SaleDTO request) {

        if(!request.hasAllValidMappings()){
            return new ResponseEntity(ObjectRequestMismatch, HttpStatus.CONFLICT);
        }

        Sale sale = request.createEntity();

        sale = saleService.save(sale);

        SuccessfulRequestExecution.setData(new SaleDTO(sale));
        return new ResponseEntity(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Response> readOne(@PathVariable Integer id) {
        Sale sale = saleService.findById(id);

        if(sale == null){
            return new ResponseEntity(ObjectForIdentityNotFound, HttpStatus.NOT_FOUND);
        }

        SuccessfulRequestExecution.setData(new SaleDTO(sale));
        return new ResponseEntity(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @GetMapping
    @Override
    public ResponseEntity<Response> readAll() {

        List<Sale> sales = saleService.findAll();
        List<SaleDTO> saleDTOS = sales.stream()
                .map(sale -> new SaleDTO(sale))
                .collect(Collectors.toList());

        SuccessfulRequestExecution.setData(saleDTOS);
        return new ResponseEntity(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<Response> update(@PathVariable Integer id, @RequestBody SaleDTO request) {

        Sale sale = saleService.findById(id);

        if(sale == null){
            return new ResponseEntity(ObjectForIdentityNotFound, HttpStatus.NOT_FOUND);
        }

        if(!request.hasAnyValidMappings()){
            return new ResponseEntity(ObjectRequestMismatch, HttpStatus.CONFLICT);
        }

        request.updateEntity(sale);

        sale = saleService.save(sale);

        SuccessfulRequestExecution.setData(new SaleDTO(sale));
        return new ResponseEntity(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Response> delete(@PathVariable Integer id) {

        Sale sale = saleService.findById(id);

        if(sale == null){
            return new ResponseEntity(ObjectForIdentityNotFound, HttpStatus.NOT_FOUND);
        }

        saleService.deleteById(id);

        SuccessfulRequestExecution.setData(null);
        return new ResponseEntity(SuccessfulRequestExecution, HttpStatus.OK);
    }
}
