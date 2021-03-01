package com.mamuya.datrastocospringbootapi.controller.crudControllers;

import com.mamuya.datrastocospringbootapi.controller.CRUDController;
import com.mamuya.datrastocospringbootapi.dto.PurchaseDTO;
import com.mamuya.datrastocospringbootapi.dto.StockDTO;
import com.mamuya.datrastocospringbootapi.entities.Purchase;
import com.mamuya.datrastocospringbootapi.service.PurchaseService;
import com.mamuya.datrastocospringbootapi.service.StockService;
import com.mamuya.datrastocospringbootapi.utility.Response;
import com.mamuya.datrastocospringbootapi.utility.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/purchases")
public class PurchaseCRUDController implements CRUDController<PurchaseDTO>, ServerResponse {

    private PurchaseService purchaseService;
    private StockService stockService;

    @Autowired
    public PurchaseCRUDController(PurchaseService purchaseService, StockService stockService) {
        this.purchaseService = purchaseService;
        this.stockService = stockService;
    }


    @PostMapping
    @Override
    public ResponseEntity<Response> create(@RequestBody PurchaseDTO request) {

        if(!request.hasAllValidMappings()){
            return new ResponseEntity<>(ObjectRequestMismatch, HttpStatus.CONFLICT);
        }

        if(!stockService.existsById(request.getStock().getId())){
            return new ResponseEntity<>(ObjectForIdentityNotFound, HttpStatus.NOT_FOUND);
        }

        request.setStock(new StockDTO(stockService.findById(request.getStock().getId())));

        double q0 = request.getStock().getStckQnty();
        request.getStock().setStckQnty(q0 + request.getQuantity());

        Purchase purchase = request.createEntity();

        stockService.save(purchase.getStock());

        purchase = purchaseService.save(purchase);

        SuccessfulRequestExecution.setData(new PurchaseDTO(purchase));
        return new ResponseEntity<>(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Response> readOne(@PathVariable Integer id) {
        Purchase purchase = purchaseService.findById(id);

        if(purchase == null){
            return new ResponseEntity<>(ObjectForIdentityNotFound, HttpStatus.NOT_FOUND);
        }

        SuccessfulRequestExecution.setData(new PurchaseDTO(purchase));
        return new ResponseEntity<>(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @GetMapping
    @Override
    public ResponseEntity<Response> readAll() {

        List<Purchase> purchases = purchaseService.findAll();
        List<PurchaseDTO> purchaseDTOS = purchases.stream()
                .map(purchase -> new PurchaseDTO(purchase))
                .collect(Collectors.toList());

        SuccessfulRequestExecution.setData(purchaseDTOS);
        return new ResponseEntity<>(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<Response> update(@PathVariable Integer id, @RequestBody PurchaseDTO request) {

        Purchase purchase = purchaseService.findById(id);

        if(purchase == null){
            return new ResponseEntity<>(ObjectForIdentityNotFound, HttpStatus.NOT_FOUND);
        }

        if(!request.hasAnyValidMappings()){
            return new ResponseEntity<>(ObjectRequestMismatch, HttpStatus.CONFLICT);
        }

        request.updateEntity(purchase);

        if(request.isQuantityChanged()){
            stockService.save(purchase.getStock());
        }

        purchase = purchaseService.save(purchase);

        SuccessfulRequestExecution.setData(new PurchaseDTO(purchase));
        return new ResponseEntity<>(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Response> delete(@PathVariable Integer id) {

        if(!purchaseService.existsById(id)){
            return new ResponseEntity<>(ObjectForIdentityNotFound, HttpStatus.NOT_FOUND);
        }

        Purchase purchase = purchaseService.findById(id);

        double q0 = purchase.getStock().getQuantity();
        purchase.getStock().setQuantity(q0 - purchase.getQuantity());

        stockService.save(purchase.getStock());

        purchaseService.deleteById(id);

        SuccessfulRequestExecution.setData(null);
        return new ResponseEntity<>(SuccessfulRequestExecution, HttpStatus.OK);
    }
}
