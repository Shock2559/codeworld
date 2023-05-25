package com.shock.codeworld.codeworld.controller.user;

import com.shock.codeworld.codeworld.controller.auth.AuthenticationResponse;
import com.shock.codeworld.codeworld.controller.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
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

    @CrossOrigin
    @PostMapping("/create-admin")
    public AuthenticationResponse createAdmin(@RequestBody RegisterRequest request) {
        return service.createAdmin(request);
    }


    @CrossOrigin
    @GetMapping("/get-user-by-role")
    public List<ResponseUserData> getUserByRole(@RequestParam(required = false) String role) {
        return service.getUserByRole(role);
    }


    @CrossOrigin
    @PostMapping("/update-user")
    public ResponseUserData updateUser(@RequestBody ResponseUserData request) {
        return service.updateUser(request);
    }

}
