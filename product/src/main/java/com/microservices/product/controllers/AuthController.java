package com.microservices.product.controllers;

import com.microservices.product.dto.LoginResponse;
import com.microservices.product.models.Category;
import com.microservices.product.models.User;
import com.microservices.product.services.JwtTokenService;
import com.microservices.product.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenService jwtTokenService;
    @Autowired
    UserService userService;
    @PostMapping("signup")
    public ResponseEntity<User> signup(@RequestBody User user) {
        System.out.println(user);
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody User user) {

//        Authentication authObject = null;
//        try {
            Authentication authObject = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authObject);

            final UserDetails userDetails = userService.loadUserByUsername(user.getEmail());
            final User userData = userService.getUserByEmail(user.getEmail());
            final LoginResponse authenticationResponse = new LoginResponse();
            authenticationResponse.setAccessToken(jwtTokenService.generateToken(userDetails));
            authenticationResponse.setUserDetails(userData);
            return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);

//        } catch (BadCredentialsException e) {
//            throw new Exception("Invalid credentials");
//        }

//        return new ResponseEntity<>("Login Done", HttpStatus.OK);
    }
//
//    @GetMapping("logout")
//    public ResponseEntity<String> logout() {
//        return new ResponseEntity<>("Logout Done", HttpStatus.OK);
//    }
}
