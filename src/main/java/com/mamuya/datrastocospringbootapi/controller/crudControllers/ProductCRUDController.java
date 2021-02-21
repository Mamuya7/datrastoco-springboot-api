package com.mamuya.datrastocospringbootapi.controller.crudControllers;

import com.mamuya.datrastocospringbootapi.controller.CRUDController;
import com.mamuya.datrastocospringbootapi.dto.ProductDTO;
import com.mamuya.datrastocospringbootapi.entities.Product;
import com.mamuya.datrastocospringbootapi.service.ProductService;
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
@RequestMapping("/product")
public class ProductCRUDController implements CRUDController<ProductDTO>, ServerResponse {

    private ProductService productService;

    @Autowired
    public ProductCRUDController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @Override
    public ResponseEntity<Response> create(@RequestBody ProductDTO request) {

        if(!request.hasAllValidMappings()){
            return new ResponseEntity(ObjectRequestMismatch, HttpStatus.CONFLICT);
        }

        Product product = request.createEntity();

        product = productService.save(product);

        SuccessfulRequestExecution.setData(new ProductDTO(product));
        return new ResponseEntity(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Response> readOne(@PathVariable Integer id) {

        Product product = productService.findById(id);

        if(product == null){
            return new ResponseEntity(ObjectForIdentityNotFound, HttpStatus.NOT_FOUND);
        }

        SuccessfulRequestExecution.setData(new ProductDTO(product));
        return new ResponseEntity(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @GetMapping
    @Override
    public ResponseEntity<Response> readAll() {
        List<Product> products = productService.findAll();
        List<ProductDTO> productDTOS = products.stream()
                                            .map(product -> new ProductDTO(product))
                                        .collect(Collectors.toList());

        SuccessfulRequestExecution.setData(productDTOS);
        return new ResponseEntity(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<Response> update(@PathVariable Integer id, @RequestBody ProductDTO request) {

        Product product = productService.findById(id);

        if(product == null){
            return new ResponseEntity(ObjectForIdentityNotFound, HttpStatus.NOT_FOUND);
        }

        if(!request.hasAnyValidMappings()){
            return new ResponseEntity(ObjectRequestMismatch, HttpStatus.CONFLICT);
        }

        request.updateEntity(product);

        product = productService.save(product);

        SuccessfulRequestExecution.setData(new ProductDTO(product));
        return new ResponseEntity(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Response> delete(@PathVariable Integer id) {

        Product product = productService.findById(id);

        if(product == null){
            return new ResponseEntity(ObjectForIdentityNotFound, HttpStatus.NOT_FOUND);
        }

        productService.deleteById(id);

        SuccessfulRequestExecution.setData(null);
        return new ResponseEntity(SuccessfulRequestExecution, HttpStatus.OK);
    }
}
