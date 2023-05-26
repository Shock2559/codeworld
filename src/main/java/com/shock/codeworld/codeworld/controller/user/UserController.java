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
    @GetMapping("/get-user-by-role")
    public List<ResponseUserData> getUserByRole(@RequestParam(required = false) String role) {
        return service.getUserDataForRole(role);
    }

    @CrossOrigin
    @PostMapping("/update-user")
    public ResponseUserData updateUser(@RequestBody ResponseUserData request) {
        return service.updateUser(request);
    }


    @CrossOrigin
    @PostMapping("/update-card")
    public ResponseUserData updateCard(@RequestBody RequestUpdateCardDiscountPhoto request) {
        return service.updateCard(request.getId(), request.getCard());
    }

    @CrossOrigin
    @PostMapping("/update-discount")
    public ResponseUserData updateDiscount(@RequestBody RequestUpdateCardDiscountPhoto request) {
        return service.updateDiscount(request.getId(), request.getDiscount());
    }

    @CrossOrigin
    @PostMapping("/update-photo")
    public ResponseUserData updatePhoto(@RequestBody RequestUpdateCardDiscountPhoto request) {
        return service.updatePhoto(request.getId(), request.getPhoto());
    }


}
