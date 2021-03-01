package com.mamuya.datrastocospringbootapi.controller.crudControllers;

import com.mamuya.datrastocospringbootapi.controller.CRUDController;
import com.mamuya.datrastocospringbootapi.dto.StockBalanceDTO;
import com.mamuya.datrastocospringbootapi.entities.StockBalance;
import com.mamuya.datrastocospringbootapi.service.StockBalanceService;
import com.mamuya.datrastocospringbootapi.utility.Response;
import com.mamuya.datrastocospringbootapi.utility.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stock-balance")
public class StockBalanceCRUDController implements CRUDController<StockBalanceDTO>, ServerResponse {

    private StockBalanceService stockBalanceService;

    @Autowired
    public StockBalanceCRUDController(StockBalanceService stockBalanceService) {
        this.stockBalanceService = stockBalanceService;
    }

    @PostMapping
    @Override
    public ResponseEntity<Response> create(@RequestBody StockBalanceDTO request) {

        if(!request.hasAllValidMappings()){
            return new ResponseEntity<>(ObjectRequestMismatch, HttpStatus.CONFLICT);
        }

        StockBalance stockBalance = request.createEntity();

        stockBalance = stockBalanceService.save(stockBalance);
        stockBalance = stockBalanceService.findById(stockBalance.getId());

        SuccessfulRequestExecution.setData(new StockBalanceDTO(stockBalance));
        return new ResponseEntity<>(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Response> readOne(@PathVariable Integer id) {
        StockBalance stockBalance = stockBalanceService.findById(id);

        if(stockBalance == null){
            return new ResponseEntity<>(ObjectForIdentityNotFound, HttpStatus.NOT_FOUND);
        }

        SuccessfulRequestExecution.setData(new StockBalanceDTO(stockBalance));
        return new ResponseEntity<>(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @GetMapping
    @Override
    public ResponseEntity<Response> readAll() {

        List<StockBalance> stockBalances = stockBalanceService.findAll();
        List<StockBalanceDTO> stockBalanceDTOS = stockBalances.stream()
                .map(stockBalance -> new StockBalanceDTO(stockBalance))
                .collect(Collectors.toList());

        SuccessfulRequestExecution.setData(stockBalanceDTOS);
        return new ResponseEntity<>(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<Response> update(@PathVariable Integer id, @RequestBody StockBalanceDTO request) {

        StockBalance stockBalance = stockBalanceService.findById(id);

        if(stockBalance == null){
            return new ResponseEntity<>(ObjectForIdentityNotFound, HttpStatus.NOT_FOUND);
        }

        if(!request.hasAnyValidMappings()){
            return new ResponseEntity<>(ObjectRequestMismatch, HttpStatus.CONFLICT);
        }

        request.updateEntity(stockBalance);

        stockBalance = stockBalanceService.save(stockBalance);

        SuccessfulRequestExecution.setData(new StockBalanceDTO(stockBalance));
        return new ResponseEntity<>(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Response> delete(@PathVariable Integer id) {

        StockBalance stockBalance = stockBalanceService.findById(id);

        if(stockBalance == null){
            return new ResponseEntity<>(ObjectForIdentityNotFound, HttpStatus.NOT_FOUND);
        }

        stockBalanceService.deleteById(id);

        SuccessfulRequestExecution.setData(null);
        return new ResponseEntity<>(SuccessfulRequestExecution, HttpStatus.OK);
    }
}
