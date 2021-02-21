package com.mamuya.datrastocospringbootapi.controller;

import com.mamuya.datrastocospringbootapi.utility.Response;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;

public interface CRUDController<T> {

    ResponseEntity<Response> create(T request);

    ResponseEntity<Response> readOne(Integer id);

    ResponseEntity<Response> readAll();

    ResponseEntity<Response> update(Integer id, T request);

    ResponseEntity<Response> delete(Integer id);

}
