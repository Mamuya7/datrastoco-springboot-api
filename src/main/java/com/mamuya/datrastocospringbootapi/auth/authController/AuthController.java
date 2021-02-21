package com.mamuya.datrastocospringbootapi.auth.authController;

import com.mamuya.datrastocospringbootapi.auth.AuthRequest;
import com.mamuya.datrastocospringbootapi.auth.AuthResponse;
import com.mamuya.datrastocospringbootapi.auth.authService.AuthUserDetailsService;
import com.mamuya.datrastocospringbootapi.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

@RestController
public class AuthController {

    private AuthenticationManager authenticationManager;
    private AuthUserDetailsService userDetailsService;
    private JwtUtil jwtTokenUtil;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, AuthUserDetailsService userDetailsService, JwtUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<LinkedHashMap<String, Object>> login(@RequestBody AuthRequest authRequest) throws Exception{

        LinkedHashMap<String, Object> response = new LinkedHashMap<>();

        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        }catch(BadCredentialsException e){
//            throw new Exception("Incorrect Username or Password ", e);
            response.put("message", "Incorrect Username or Password ");

            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

        String jwstoken = jwtTokenUtil.generateToken(userDetails);

        response.put("message", "Login Successfull");
        response.put("data", new AuthResponse(jwstoken));

        return new ResponseEntity(response, HttpStatus.OK );
    }

}
