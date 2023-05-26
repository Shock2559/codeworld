package com.shock.codeworld.codeworld.controller.user;

import com.shock.codeworld.codeworld.config.JwtService;
import com.shock.codeworld.codeworld.controller.auth.AuthenticationResponse;
import com.shock.codeworld.codeworld.controller.auth.RegisterRequest;
import com.shock.codeworld.codeworld.entity.Role;
import com.shock.codeworld.codeworld.entity.User;
import com.shock.codeworld.codeworld.entity.UserData;
import com.shock.codeworld.codeworld.repository.UserDataRepository;
import com.shock.codeworld.codeworld.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserDataRepository userDataRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseUserData userdata(String token) {
        String login = jwtService.extractUsername(token);
        User user = userRepository.findListByLogin(login).get(0);

        UserData userData = userDataRepository.getUserDataForUser(user);

        return ResponseUserData.builder()
                .id(userData.getUser().getId())
                .login(login)
                .name(userData.getName())
                .email(userData.getEmail())
                .phone(userData.getPhone())
                .dateRegistration(userData.getDateRegistration())
                .address(userData.getAddress())
                .build();
    }


//    public ResponseUserData updateUser(ResponseUserData request) {
//
//        if(request.getLogin() == null || request.getId() == 0 || request.getDateBirth() == null ||
//                request.getEmail() == null || request.getName() == null || request.getPhone() == null) {
//
//            throw  new ResponseStatusException(HttpStatus.NO_CONTENT, "Not found data");
//
//        }
//
//        User user = userRepository.findByLogin(request.getLogin()).orElseThrow(()->  new ResponseStatusException(HttpStatus.NO_CONTENT, "Not found data"));
//        UserData userdata = userDataRepository.getUserDataForUser(user);
//
//
//        userdata.setName(request.getName());
//        userdata.setEmail(request.getEmail());
//        userdata.setPhone(request.getPhone());
//        userdata.setDateBirth(request.getDateBirth());
//
//        userDataRepository.save(userdata);
//
//        return ResponseUserData.builder()
//                .id(request.getId())
//                .login(request.getLogin())
//                .name(userdata.getName())
//                .email(userdata.getEmail())
//                .phone(userdata.getPhone())
//                .dateBirth(userdata.getDateBirth())
//                .valid(userdata.isValid())
//                .build();
//
//    }
}
