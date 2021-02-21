package com.mamuya.datrastocospringbootapi.controller.crudControllers;

import com.mamuya.datrastocospringbootapi.controller.CRUDController;
import com.mamuya.datrastocospringbootapi.dto.RoleDTO;
import com.mamuya.datrastocospringbootapi.entities.Role;
import com.mamuya.datrastocospringbootapi.service.RoleService;
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
@RequestMapping("/role")
public class RoleCRUDController implements CRUDController<RoleDTO>, ServerResponse {

    private RoleService roleService;

    @Autowired
    public RoleCRUDController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    @Override
    public ResponseEntity<Response> create(@RequestBody RoleDTO request) {

        if(!request.hasAllValidMappings()){
            return new ResponseEntity(ObjectRequestMismatch, HttpStatus.CONFLICT);
        }

        Role role = request.createEntity();

        role = roleService.save(role);

        SuccessfulRequestExecution.setData(new RoleDTO(role));
        return new ResponseEntity(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Response> readOne(@PathVariable Integer id) {
        Role role = roleService.findById(id);

        if(role == null){
            return new ResponseEntity(ObjectForIdentityNotFound, HttpStatus.NOT_FOUND);
        }

        SuccessfulRequestExecution.setData(new RoleDTO(role));
        return new ResponseEntity(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @GetMapping
    @Override
    public ResponseEntity<Response> readAll() {

        List<Role> roles = roleService.findAll();
        List<RoleDTO> roleDTOS = roles.stream()
                .map(purchase -> new RoleDTO(purchase))
                .collect(Collectors.toList());

        SuccessfulRequestExecution.setData(roleDTOS);
        return new ResponseEntity(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<Response> update(@PathVariable Integer id, @RequestBody RoleDTO request) {

        Role role = roleService.findById(id);

        if(role == null){
            return new ResponseEntity(ObjectForIdentityNotFound, HttpStatus.NOT_FOUND);
        }

        if(!request.hasAnyValidMappings()){
            return new ResponseEntity(ObjectRequestMismatch, HttpStatus.CONFLICT);
        }

        request.updateEntity(role);

        role = roleService.save(role);

        SuccessfulRequestExecution.setData(new RoleDTO(role));
        return new ResponseEntity(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Response> delete(@PathVariable Integer id) {

        Role role = roleService.findById(id);

        if(role == null){
            return new ResponseEntity(ObjectForIdentityNotFound, HttpStatus.NOT_FOUND);
        }

        roleService.deleteById(id);

        SuccessfulRequestExecution.setData(null);
        return new ResponseEntity(SuccessfulRequestExecution, HttpStatus.OK);
    }
}
