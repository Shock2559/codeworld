package com.shock.codeworld.codeworld.controller.auth;

import com.shock.codeworld.codeworld.config.JwtService;
import com.shock.codeworld.codeworld.entity.Role;
import com.shock.codeworld.codeworld.entity.User;
import com.shock.codeworld.codeworld.entity.UserData;
import com.shock.codeworld.codeworld.repository.UserDataRepository;
import com.shock.codeworld.codeworld.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final UserDataRepository userDataRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        String status = "ok";
        String token = "";

        List<User> userList = userRepository.findListByLogin(request.getLogin());

        if(userList.size() > 0) {
            status = "error: a user with this username already exists";
        } else {
            User user = User.builder()
                    .login(request.getLogin())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.USER)
                    .build();

            userRepository.save(user);

            UserData userData = UserData.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .phone(request.getPhone())
                    .dateBirth(request.getDateBirth())
                    .user(user)
                    .build();

            userDataRepository.save(userData);


            token = jwtService.generateToken(user);
        }

        return AuthenticationResponse.builder()
                .token(token)
                .status(status)
                .build();
    }


    public AuthenticationResponse authentication(AuthenticationRequest request) {
        String status = "ok";
        String token = "";

        authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
                  request.getLogin(),
                  request.getPassword()
          )
        );

        List<User> user = userRepository.findListByLogin(request.getLogin());

        if(user.size() > 0) {
            token = jwtService.generateToken(user.get(0));
        } else {
            status = "error";
        }

        return AuthenticationResponse.builder()
                .token(token)
                .status(status)
                .build();
    }
}
