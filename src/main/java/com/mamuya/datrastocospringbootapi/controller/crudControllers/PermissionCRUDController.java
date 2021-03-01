package com.mamuya.datrastocospringbootapi.controller.crudControllers;

import com.mamuya.datrastocospringbootapi.controller.CRUDController;
import com.mamuya.datrastocospringbootapi.dto.PermissionDTO;
import com.mamuya.datrastocospringbootapi.entities.Permission;
import com.mamuya.datrastocospringbootapi.service.PermissionService;
import com.mamuya.datrastocospringbootapi.utility.Response;
import com.mamuya.datrastocospringbootapi.utility.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/permission")
public class PermissionCRUDController implements CRUDController<PermissionDTO>, ServerResponse {

    private PermissionService permissionService;

    @Autowired
    public PermissionCRUDController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping
    @Override
    public ResponseEntity<Response> create(@RequestBody PermissionDTO request) {

        if(!request.hasAllValidMappings()){
            return new ResponseEntity<>(ObjectRequestMismatch, HttpStatus.CONFLICT);
        }

        Permission permission = request.createEntity();

        permission = permissionService.save(permission);

        SuccessfulRequestExecution.setData(permission);
        return new ResponseEntity<>(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Response> readOne(@PathVariable Integer id) {

        Permission permission = permissionService.findById(id);

        if(permission == null){
            return  new ResponseEntity<>(ObjectForIdentityNotFound, HttpStatus.NOT_FOUND);
        }

        SuccessfulRequestExecution.setData(permission);
        return new ResponseEntity<>(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @GetMapping
    @Override
    public ResponseEntity<Response> readAll() {

        List<Permission> permissions = permissionService.findAll();

        List<PermissionDTO> permissionDTOS = permissions.stream()
                                    .map(permission -> new PermissionDTO(permission))
                                .collect(Collectors.toList());

        SuccessfulRequestExecution.setData(permissionDTOS);
        return new ResponseEntity<>(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<Response> update(@PathVariable Integer id, @RequestBody PermissionDTO request) {

        Permission permission = permissionService.findById(id);

        if(permission == null){
            return  new ResponseEntity<>(ObjectForIdentityNotFound, HttpStatus.NOT_FOUND);
        }

        if(!request.hasAnyValidMappings()){
            return new ResponseEntity<>(ObjectRequestMismatch, HttpStatus.CONFLICT);
        }

        request.updateEntity(permission);

        permissionService.save(permission);

        SuccessfulRequestExecution.setData(new PermissionDTO(permission));
        return new ResponseEntity<>(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Response> delete(@PathVariable Integer id) {

        Permission permission = permissionService.findById(id);

        if(permission == null){
            return  new ResponseEntity<>(ObjectForIdentityNotFound, HttpStatus.NOT_FOUND);
        }

        permissionService.deleteById(id);

        SuccessfulRequestExecution.setData(null);
        return new ResponseEntity<>(SuccessfulRequestExecution, HttpStatus.OK);
    }
}
