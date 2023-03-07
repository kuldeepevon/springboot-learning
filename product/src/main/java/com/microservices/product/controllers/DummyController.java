package com.microservices.product.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/dummy")
public class DummyController {
    @PostMapping("foo")
    public ResponseEntity<String> foo() {
        return new ResponseEntity<>("Foo Request", HttpStatus.OK);
    }
}
