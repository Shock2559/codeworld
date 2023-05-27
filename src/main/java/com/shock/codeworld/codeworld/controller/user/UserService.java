package com.shock.codeworld.codeworld.controller.user;

import com.shock.codeworld.codeworld.config.JwtService;
import com.shock.codeworld.codeworld.controller.auth.AuthenticationResponse;
import com.shock.codeworld.codeworld.controller.auth.RegisterRequest;
import com.shock.codeworld.codeworld.controller.basket.BasketService;
import com.shock.codeworld.codeworld.controller.basket.ResponseBasket;
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

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserDataRepository userDataRepository;
    private final PasswordEncoder passwordEncoder;
    private final BasketService basketService;

    public ResponseUserData userdata(String token) {
        String login = jwtService.extractUsername(token);
        User user = userRepository.findListByLogin(login).get(0);

        UserData userData = userDataRepository.getUserDataForUser(user);

        ResponseBasket basket = basketService.getActionBasket();

        return ResponseUserData.builder()
                .id(userData.getId())
                .login(login)
                .name(userData.getName())
                .email(userData.getEmail())
                .phone(userData.getPhone())
                .dateRegistration(userData.getDateRegistration())
                .address(userData.getAddress())
                .role(userData.getUser().getRole())
                .discount(userData.getDiscount())
                .card(userData.getCard())
                .photo(userData.getPhoto())
                .id_basket((basket != null)? basket.getId() : 0)
                .build();
    }


    public List<ResponseUserData> getUserDataForRole(String role) {

        List<UserData> usersData = new ArrayList<>();

        System.out.println(role);

        if(role == null) {
            usersData = userDataRepository.getUserDataForAllRole(Role.ADMIN);
        } else {

            if(role.equals("USER")) {
                usersData = userDataRepository.getUserDataForRole(Role.USER);
            }

            if(role.equals("FARMER")) {
                usersData = userDataRepository.getUserDataForRole(Role.FARMER);
            }

            if(!role.equals("USER") && !role.equals("FARMER")) {
                usersData = userDataRepository.getUserDataForAllRole(Role.ADMIN);
            }

        }

        List<ResponseUserData> response = new ArrayList<>();

        for(int i = 0; i < usersData.size(); i++) {
            response.add(ResponseUserData.builder()
                    .id(usersData.get(i).getUser().getId())
                    .login(usersData.get(i).getUser().getLogin())
                    .name(usersData.get(i).getName())
                    .email(usersData.get(i).getEmail())
                    .phone(usersData.get(i).getPhone())
                    .dateRegistration(usersData.get(i).getDateRegistration())
                    .address(usersData.get(i).getAddress())
                    .photo(usersData.get(i).getPhoto())
                    .discount(usersData.get(i).getDiscount())
                    .card(usersData.get(i).getCard())
                    .build());
        }

        return response;

    }

    public ResponseUserData updateUser(ResponseUserData request) {

        if(request.getLogin() == null || request.getRole() == null || request.getAddress() == null ||
                request.getEmail() == null || request.getName() == null || request.getPhone() == null) {

            throw  new ResponseStatusException(HttpStatus.NO_CONTENT, "Not found data");

        }

        User user = userRepository.findByLogin(request.getLogin()).orElseThrow(()->  new ResponseStatusException(HttpStatus.NO_CONTENT, "Not found data"));
        UserData userdata = userDataRepository.getUserDataForUser(user);


        userdata.setName(request.getName());
        userdata.setEmail(request.getEmail());
        userdata.setPhone(request.getPhone());
        userdata.setAddress(request.getAddress());

        userDataRepository.save(userdata);

        return ResponseUserData.builder()
                .id(userdata.getId())
                .login(request.getLogin())
                .name(userdata.getName())
                .email(userdata.getEmail())
                .phone(userdata.getPhone())
                .dateRegistration(userdata.getDateRegistration())
                .address(userdata.getAddress())
                .photo(userdata.getPhoto())
                .discount(userdata.getDiscount())
                .card(userdata.getCard())
                .build();
    }


    public ResponseUserData updateCard(Integer id, String card) {

        UserData userdata = userDataRepository.my_getUserDataById(id);

        userdata.setCard(card);

        userDataRepository.save(userdata);

        return ResponseUserData.builder()
                .id(userdata.getId())
                .login(userdata.getUser().getLogin())
                .name(userdata.getName())
                .email(userdata.getEmail())
                .phone(userdata.getPhone())
                .dateRegistration(userdata.getDateRegistration())
                .address(userdata.getAddress())
                .photo(userdata.getPhoto())
                .discount(userdata.getDiscount())
                .card(userdata.getCard())
                .build();
    }

    public ResponseUserData updateDiscount(Integer id, Integer discount) {

        UserData userdata = userDataRepository.my_getUserDataById(id);

        userdata.setDiscount(discount);

        userDataRepository.save(userdata);

        return ResponseUserData.builder()
                .id(userdata.getId())
                .login(userdata.getUser().getLogin())
                .name(userdata.getName())
                .email(userdata.getEmail())
                .phone(userdata.getPhone())
                .dateRegistration(userdata.getDateRegistration())
                .address(userdata.getAddress())
                .photo(userdata.getPhoto())
                .discount(userdata.getDiscount())
                .card(userdata.getCard())
                .build();
    }

    public ResponseUserData updatePhoto(Integer id, String photo) {

        UserData userdata = userDataRepository.my_getUserDataById(id);

        userdata.setPhoto(photo);

        userDataRepository.save(userdata);

        return ResponseUserData.builder()
                .id(userdata.getId())
                .login(userdata.getUser().getLogin())
                .name(userdata.getName())
                .email(userdata.getEmail())
                .phone(userdata.getPhone())
                .dateRegistration(userdata.getDateRegistration())
                .address(userdata.getAddress())
                .photo(userdata.getPhoto())
                .discount(userdata.getDiscount())
                .card(userdata.getCard())
                .build();
    }


}

