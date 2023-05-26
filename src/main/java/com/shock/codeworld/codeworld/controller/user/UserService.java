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
                .dateBirth(userData.getDateBirth())
                .valid(userData.isValid())
                .build();
    }

    public AuthenticationResponse createAdmin(RegisterRequest request) {
        String status = "ok";
        String token = "";

        List<User> userList = userRepository.findListByLogin(request.getLogin());

        if(userList.size() > 0) {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "This user already exists");

        } else {
            if(request.getLogin() == null || request.getPassword() == null || request.getDateBirth() == null ||
                    request.getEmail() == null || request.getName() == null || request.getPhone() == null) {

                throw  new ResponseStatusException(HttpStatus.NO_CONTENT, "Not found data");
            } else {
                User user = User.builder()
                        .login(request.getLogin())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .role(Role.ADMIN)
                        .build();

                userRepository.save(user);

                UserData userData = UserData.builder()
                        .name(request.getName())
                        .email(request.getEmail())
                        .phone(request.getPhone())
                        .dateBirth(request.getDateBirth())
                        .user(user)
                        .isValid(true)
                        .build();

                userDataRepository.save(userData);

                token = jwtService.generateToken(user);
            }
        }

        return AuthenticationResponse.builder()
                .token(token)
                .status(status)
                .build();
    }

    public List<ResponseUserData> getUserByRole(String role) {

        List<UserData> usersData = new ArrayList<>();

        System.out.println(role);

        if(role == null) {
            usersData = userDataRepository.getUserDataForAllRole(Role.SUPERUSER);
        } else {

            if(role.equals("USER")) {
                usersData = userDataRepository.getUserDataForRole(Role.USER);
            }

            if(role.equals("ADMIN")) {
                usersData = userDataRepository.getUserDataForRole(Role.ADMIN);
            }

            if(!role.equals("USER") && !role.equals("ADMIN")) {
                usersData = userDataRepository.getUserDataForAllRole(Role.SUPERUSER);
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
                    .dateBirth(usersData.get(i).getDateBirth())
                    .valid(usersData.get(i).isValid())
                    .build());
        }

        return response;

    }

    public ResponseUserData updateUser(ResponseUserData request) {

        if(request.getLogin() == null || request.getId() == 0 || request.getDateBirth() == null ||
                request.getEmail() == null || request.getName() == null || request.getPhone() == null) {

            throw  new ResponseStatusException(HttpStatus.NO_CONTENT, "Not found data");

        }

        User user = userRepository.findByLogin(request.getLogin()).orElseThrow(()->  new ResponseStatusException(HttpStatus.NO_CONTENT, "Not found data"));
        UserData userdata = userDataRepository.getUserDataForUser(user);


        userdata.setName(request.getName());
        userdata.setEmail(request.getEmail());
        userdata.setPhone(request.getPhone());
        userdata.setDateBirth(request.getDateBirth());

        userDataRepository.save(userdata);

        return ResponseUserData.builder()
                .id(request.getId())
                .login(request.getLogin())
                .name(userdata.getName())
                .email(userdata.getEmail())
                .phone(userdata.getPhone())
                .dateBirth(userdata.getDateBirth())
                .valid(userdata.isValid())
                .build();

    }
}
