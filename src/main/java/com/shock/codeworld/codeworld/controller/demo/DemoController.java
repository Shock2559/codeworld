package com.shock.codeworld.codeworld.controller.demo;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/demo")
@RequiredArgsConstructor
public class DemoController {



    @GetMapping()
    public String demo() {
        return "Hello! This is demo";
    }

}