package com.mamuya.datrastocospringbootapi.controller.crudControllers;

import com.mamuya.datrastocospringbootapi.controller.CRUDController;
import com.mamuya.datrastocospringbootapi.dto.SaleDTO;
import com.mamuya.datrastocospringbootapi.dto.StockDTO;
import com.mamuya.datrastocospringbootapi.entities.Sale;
import com.mamuya.datrastocospringbootapi.service.SaleService;
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
@RequestMapping("/sales")
public class SaleCRUDController implements CRUDController<SaleDTO>, ServerResponse {

    private SaleService saleService;
    private StockService stockService;

    @Autowired
    public SaleCRUDController(SaleService saleService, StockService stockService) {
        this.saleService = saleService;
        this.stockService = stockService;
    }

    @PostMapping
    @Override
    public ResponseEntity<Response> create(@RequestBody SaleDTO request) {

        if(!request.hasAllValidMappings()){
            return new ResponseEntity<>(ObjectRequestMismatch, HttpStatus.CONFLICT);
        }

        if(!stockService.existsById(request.getStock().getId())){
            return new ResponseEntity<>(ObjectForIdentityNotFound, HttpStatus.NOT_FOUND);
        }

        request.setStock(new StockDTO(stockService.findById(request.getStock().getId())));

        double q1 = request.getStock().getStckQnty();
        request.getStock().setStckQnty(q1 - request.getQuantity());

        Sale sale = request.createEntity();

        stockService.save(sale.getStock());

        sale = saleService.save(sale);

        SuccessfulRequestExecution.setData(new SaleDTO(sale));
        return new ResponseEntity<>(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Response> readOne(@PathVariable Integer id) {

        if(!saleService.existsById(id)){
            return new ResponseEntity<>(ObjectForIdentityNotFound, HttpStatus.NOT_FOUND);
        }

        Sale sale = saleService.findById(id);

        SuccessfulRequestExecution.setData(new SaleDTO(sale));
        return new ResponseEntity<>(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @GetMapping
    @Override
    public ResponseEntity<Response> readAll() {

        List<Sale> sales = saleService.findAll();
        List<SaleDTO> saleDTOS = sales.stream()
                .map(sale -> new SaleDTO(sale))
                .collect(Collectors.toList());

        SuccessfulRequestExecution.setData(saleDTOS);
        return new ResponseEntity<>(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<Response> update(@PathVariable Integer id, @RequestBody SaleDTO request) {

        if(!saleService.existsById(id)){
            return new ResponseEntity<>(ObjectForIdentityNotFound, HttpStatus.NOT_FOUND);
        }

        Sale sale = saleService.findById(id);

        if(!request.hasAnyValidMappings()){
            return new ResponseEntity<>(ObjectRequestMismatch, HttpStatus.CONFLICT);
        }

        request.updateEntity(sale);

        if(request.isQuantityChanged()){
            stockService.save(sale.getStock());
        }

        sale = saleService.save(sale);

        SuccessfulRequestExecution.setData(new SaleDTO(sale));
        return new ResponseEntity<>(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Response> delete(@PathVariable Integer id) {

        if(!saleService.existsById(id)){
            return new ResponseEntity<>(ObjectForIdentityNotFound, HttpStatus.NOT_FOUND);
        }

        Sale sale = saleService.findById(id);

        double q0 = sale.getStock().getQuantity();
        sale.getStock().setQuantity(q0 + sale.getQuantity());

        stockService.save(sale.getStock());

        saleService.deleteById(id);

        SuccessfulRequestExecution.setData(null);
        return new ResponseEntity<>(SuccessfulRequestExecution, HttpStatus.OK);
    }
}
