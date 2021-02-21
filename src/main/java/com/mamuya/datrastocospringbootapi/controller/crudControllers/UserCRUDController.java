package com.mamuya.datrastocospringbootapi.controller.crudControllers;

import com.mamuya.datrastocospringbootapi.controller.CRUDController;
import com.mamuya.datrastocospringbootapi.dto.UserDTO;
import com.mamuya.datrastocospringbootapi.entities.User;
import com.mamuya.datrastocospringbootapi.service.UserService;
import com.mamuya.datrastocospringbootapi.utility.Response;
import com.mamuya.datrastocospringbootapi.utility.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserCRUDController implements CRUDController<UserDTO>, ServerResponse {

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserCRUDController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    @Override
    public ResponseEntity<Response> create(@RequestBody UserDTO request) {

        if(!request.hasAllValidMappings()){
            return new ResponseEntity(ObjectRequestMismatch, HttpStatus.CONFLICT);
        }

        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User user = request.createEntity();

        if(user == null)
            return new ResponseEntity(ObjectRequestMismatch, HttpStatus.CONFLICT);

        SuccessfulRequestExecution.setData(userService.save(user));

        return new ResponseEntity(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Response> readOne(@PathVariable Integer id) {

        User user  = userService.findById(id);
        if (user == null){
            return new ResponseEntity(ObjectForIdentityNotFound, HttpStatus.NOT_FOUND);
        }

        UserDTO dto = new UserDTO(user);
        SuccessfulRequestExecution.setData(dto);

        return new ResponseEntity(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @GetMapping
    @Override
    public ResponseEntity<Response> readAll() {
        List<User> users = userService.findAll();
        List<UserDTO> dtos = users.stream()
                                .map(user -> new UserDTO(user))
                                .collect(Collectors.toList());

        SuccessfulRequestExecution.setData(dtos);

        return new ResponseEntity(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<Response> update(@PathVariable Integer id, @RequestBody UserDTO request) {

        User user  = userService.findById(id);
        if (user == null){
            return new ResponseEntity(ObjectForIdentityNotFound, HttpStatus.NOT_FOUND);
        }

        if(!request.hasAnyValidMappings()){
            return new ResponseEntity(ObjectRequestMismatch, HttpStatus.CONFLICT);
        }

        request.updateEntity(user);

        userService.save(user);

        SuccessfulRequestExecution.setData(new UserDTO(user));

        return new ResponseEntity(SuccessfulRequestExecution, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Response> delete(@PathVariable Integer id) {

        User user  = userService.findById(id);
        if (user == null){
            return new ResponseEntity(ObjectForIdentityNotFound, HttpStatus.NOT_FOUND);
        }

        userService.deleteById(id);
        SuccessfulRequestExecution.setData(null);

        return new ResponseEntity(SuccessfulRequestExecution, HttpStatus.OK);
    }
}
