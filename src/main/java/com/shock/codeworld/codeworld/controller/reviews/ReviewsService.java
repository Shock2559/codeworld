package com.shock.codeworld.codeworld.controller.reviews;

import com.shock.codeworld.codeworld.entity.Reviews;
import com.shock.codeworld.codeworld.entity.Role;
import com.shock.codeworld.codeworld.entity.User;
import com.shock.codeworld.codeworld.entity.UserData;
import com.shock.codeworld.codeworld.repository.ReviewsRepository;
import com.shock.codeworld.codeworld.repository.UserDataRepository;
import com.shock.codeworld.codeworld.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ReviewsService {


    private final UserRepository userRepository;
    private final UserDataRepository userDataRepository;
    private final ReviewsRepository reviewsRepository;

    public ResponseReviews addReviews(ResponseReviews request) {

        User user = userRepository.findById(request.getUser()).orElseThrow(()->  new ResponseStatusException(HttpStatus.NO_CONTENT, "Not found data"));
        UserData userData = userDataRepository.getUserDataForUser(user);

        User farmer = userRepository.findById(request.getFarmer()).orElseThrow(()->  new ResponseStatusException(HttpStatus.NO_CONTENT, "Not found data"));
        UserData userDataFarmer = userDataRepository.getUserDataForUser(farmer);

        Reviews reviews = Reviews.builder()
                .user(userData)
                .farmer(userDataFarmer)
                .reviews(request.getReviews())
                .assessment(request.getAssessment())
                .build();

        reviewsRepository.save(reviews);

        return ResponseReviews.builder()
                .id(request.getId())

                .build();
    }
}