package com.mamuya.datrastocospringbootapi.controller.crudControllers;

import com.mamuya.datrastocospringbootapi.controller.CRUDController;
import com.mamuya.datrastocospringbootapi.dto.StockDTO;
import com.mamuya.datrastocospringbootapi.entities.Stock;
import com.mamuya.datrastocospringbootapi.service.StockService;
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
@RequestMapping("/stock")
public class StockCRUDController implements CRUDController<StockDTO>, ServerResponse {

    private StockService stockService;

    @Autowired
    public StockCRUDController(StockService stockService) {
        this.stockService = stockService;
    } @PostMapping
    @Override
    public ResponseEntity<Response> create(@RequestBody StockDTO request) {

        if(!request.hasAllValidMappings()){
            return new ResponseEntity(ObjectRequestMismatch, HttpStatus.CONFLICT);
        }

        Stock stock = request.createEntity();

        stock = stockService.save(stock);

        SuccessfulRequestExecution.setData(new StockDTO(stock));
        return new ResponseEntity(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Response> readOne(@PathVariable Integer id) {
        Stock stock = stockService.findById(id);

        if(stock == null){
            return new ResponseEntity(ObjectForIdentityNotFound, HttpStatus.NOT_FOUND);
        }

        SuccessfulRequestExecution.setData(new StockDTO(stock));
        return new ResponseEntity(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @GetMapping
    @Override
    public ResponseEntity<Response> readAll() {

        List<Stock> stocks = stockService.findAll();
        List<StockDTO> stockDTOS = stocks.stream()
                .map(stock -> new StockDTO(stock))
                .collect(Collectors.toList());

        SuccessfulRequestExecution.setData(stockDTOS);
        return new ResponseEntity(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<Response> update(@PathVariable Integer id, @RequestBody StockDTO request) {

        Stock stock = stockService.findById(id);

        if(stock == null){
            return new ResponseEntity(ObjectForIdentityNotFound, HttpStatus.NOT_FOUND);
        }

        if(!request.hasAnyValidMappings()){
            return new ResponseEntity(ObjectRequestMismatch, HttpStatus.CONFLICT);
        }

        request.updateEntity(stock);

        stock = stockService.save(stock);

        SuccessfulRequestExecution.setData(new StockDTO(stock));
        return new ResponseEntity(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Response> delete(@PathVariable Integer id) {

        Stock stock = stockService.findById(id);

        if(stock == null){
            return new ResponseEntity(ObjectForIdentityNotFound, HttpStatus.NOT_FOUND);
        }

        stockService.deleteById(id);

        SuccessfulRequestExecution.setData(null);
        return new ResponseEntity(SuccessfulRequestExecution, HttpStatus.OK);
    }
}
