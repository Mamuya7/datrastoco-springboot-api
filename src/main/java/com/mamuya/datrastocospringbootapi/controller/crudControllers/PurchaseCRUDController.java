package com.mamuya.datrastocospringbootapi.controller.crudControllers;

import com.mamuya.datrastocospringbootapi.controller.CRUDController;
import com.mamuya.datrastocospringbootapi.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/purchase")
public class PurchaseCRUDController implements CRUDController {

    private PurchaseService purchaseService;

    @Autowired
    public PurchaseCRUDController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    @Override
    public ResponseEntity<LinkedHashMap<String, Object>> create(@RequestBody LinkedHashMap<String, Object> request) {
        return null;
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<LinkedHashMap<String, Object>> readOne(@PathVariable Integer id) {
        return null;
    }

    @GetMapping
    @Override
    public ResponseEntity<LinkedHashMap<String, Object>> readAll() {
        return null;
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<LinkedHashMap<String, Object>> update(@PathVariable Integer id, @RequestBody LinkedHashMap<String, Object> request) {
        return null;
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<LinkedHashMap<String, Object>> delete(@PathVariable Integer id) {
        return null;
    }
}