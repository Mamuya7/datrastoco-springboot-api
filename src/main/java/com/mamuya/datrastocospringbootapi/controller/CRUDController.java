package com.mamuya.datrastocospringbootapi.controller;

import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;

public interface CRUDController {

    ResponseEntity<LinkedHashMap<String, Object>> create(LinkedHashMap<String, Object> request);

    ResponseEntity<LinkedHashMap<String, Object>> readOne(Integer id);

    ResponseEntity<LinkedHashMap<String, Object>> readAll();

    ResponseEntity<LinkedHashMap<String, Object>> update(Integer id, LinkedHashMap<String, Object> request);

    ResponseEntity<LinkedHashMap<String, Object>> delete(Integer id);

}
