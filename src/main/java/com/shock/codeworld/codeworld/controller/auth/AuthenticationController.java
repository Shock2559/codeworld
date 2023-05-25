package com.shock.codeworld.codeworld.controller.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @CrossOrigin
    @PostMapping("/signout")
    public String signout() {
        return "";
    }

    @CrossOrigin
    @PostMapping("/registration")
    public AuthenticationResponse register(@RequestBody RegisterRequest request) {
        return service.register(request);
    }

    @CrossOrigin
    @PostMapping("/authentication")
    public AuthenticationResponse authentication(@RequestBody AuthenticationRequest request) {
        return service.authentication(request);
    }



}
