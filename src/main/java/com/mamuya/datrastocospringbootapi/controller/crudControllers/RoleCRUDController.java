package com.mamuya.datrastocospringbootapi.controller.crudControllers;

import com.mamuya.datrastocospringbootapi.controller.CRUDController;
import com.mamuya.datrastocospringbootapi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/role")
public class RoleCRUDController implements CRUDController {

    private RoleService roleService;

    @Autowired
    public RoleCRUDController(RoleService roleService) {
        this.roleService = roleService;
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