package com.shock.codeworld.codeworld.controller.user;

import com.shock.codeworld.codeworld.config.JwtService;
import com.shock.codeworld.codeworld.entity.User;
import com.shock.codeworld.codeworld.entity.UserData;
import com.shock.codeworld.codeworld.repository.UserDataRepository;
import com.shock.codeworld.codeworld.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserDataRepository userDataRepository;

    public ResponseUserData userdata(String token) {
        String login = jwtService.extractUsername(token);
        User user = userRepository.findListByLogin(login).get(0);

        UserData userData = userDataRepository.getUserDataForUser(user);

        return ResponseUserData.builder()
                .name(userData.getName())
                .email(userData.getEmail())
                .phone(userData.getPhone())
                .dateBirth(userData.getDateBirth())
                .build();
    }

}
