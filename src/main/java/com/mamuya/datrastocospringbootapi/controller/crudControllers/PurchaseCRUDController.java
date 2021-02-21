package com.mamuya.datrastocospringbootapi.controller.crudControllers;

import com.mamuya.datrastocospringbootapi.controller.CRUDController;
import com.mamuya.datrastocospringbootapi.dto.PurchaseDTO;
import com.mamuya.datrastocospringbootapi.entities.Purchase;
import com.mamuya.datrastocospringbootapi.service.PurchaseService;
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
@RequestMapping("/purchase")
public class PurchaseCRUDController implements CRUDController<PurchaseDTO>, ServerResponse {

    private PurchaseService purchaseService;

    @Autowired
    public PurchaseCRUDController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    @Override
    public ResponseEntity<Response> create(@RequestBody PurchaseDTO request) {

        if(!request.hasAllValidMappings()){
            return new ResponseEntity(ObjectRequestMismatch, HttpStatus.CONFLICT);
        }

        Purchase purchase = request.createEntity();

        purchase = purchaseService.save(purchase);

        SuccessfulRequestExecution.setData(new PurchaseDTO(purchase));
        return new ResponseEntity(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Response> readOne(@PathVariable Integer id) {
        Purchase purchase = purchaseService.findById(id);

        if(purchase == null){
            return new ResponseEntity(ObjectForIdentityNotFound, HttpStatus.NOT_FOUND);
        }

        SuccessfulRequestExecution.setData(new PurchaseDTO(purchase));
        return new ResponseEntity(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @GetMapping
    @Override
    public ResponseEntity<Response> readAll() {

        List<Purchase> purchases = purchaseService.findAll();
        List<PurchaseDTO> purchaseDTOS = purchases.stream()
                .map(purchase -> new PurchaseDTO(purchase))
                .collect(Collectors.toList());

        SuccessfulRequestExecution.setData(purchaseDTOS);
        return new ResponseEntity(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<Response> update(@PathVariable Integer id, @RequestBody PurchaseDTO request) {

        Purchase purchase = purchaseService.findById(id);

        if(purchase == null){
            return new ResponseEntity(ObjectForIdentityNotFound, HttpStatus.NOT_FOUND);
        }

        if(!request.hasAnyValidMappings()){
            return new ResponseEntity(ObjectRequestMismatch, HttpStatus.CONFLICT);
        }

        request.updateEntity(purchase);

        purchase = purchaseService.save(purchase);

        SuccessfulRequestExecution.setData(new PurchaseDTO(purchase));
        return new ResponseEntity(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Response> delete(@PathVariable Integer id) {

        Purchase purchase = purchaseService.findById(id);

        if(purchase == null){
            return new ResponseEntity(ObjectForIdentityNotFound, HttpStatus.NOT_FOUND);
        }

        purchaseService.deleteById(id);

        SuccessfulRequestExecution.setData(null);
        return new ResponseEntity(SuccessfulRequestExecution, HttpStatus.OK);
    }
}
