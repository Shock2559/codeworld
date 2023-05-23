package com.shock.codeworld.codeworld.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @CrossOrigin
    @GetMapping("/userdata")
    public ResponseUserData userdata(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {

        String jwt = authHeader.substring(7);

        return service.userdata(jwt);
    }

}
