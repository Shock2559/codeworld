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

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewsService {


    private final UserRepository userRepository;
    private final UserDataRepository userDataRepository;
    private final ReviewsRepository reviewsRepository;

    public ResponseReviews addReviews(ResponseReviews request) {

        UserData userData = userDataRepository.my_getUserDataById(request.getUser());

        UserData userDataFarmer = userDataRepository.my_getUserDataById(request.getFarmer());

        Reviews reviews = Reviews.builder()
                .user(userData)
                .farmer(userDataFarmer)
                .reviews(request.getReviews())
                .assessment(request.getAssessment())
                .build();

        reviewsRepository.save(reviews);

        return ResponseReviews.builder()
                .id(request.getId())
                .user(userData.getId())
                .farmer(userDataFarmer.getId())
                .nameUser(userData.getName())
                .reviews(reviews.getReviews())
                .assessment(request.getAssessment())
                .build();
    }

    public List<ResponseReviews> getReviewById(Integer id) {

        List<ResponseReviews> response = new ArrayList<>();

        if(id == null) {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found data");
        }

        List<Reviews> reviews = reviewsRepository.my_getReviewsByIdFarmer(id);

        for(int i = 0; i < reviews.size(); i++) {
            response.add(ResponseReviews.builder()
                    .id(reviews.get(i).getId())
                    .user(reviews.get(i).getUser().getId())
                    .farmer(reviews.get(i).getFarmer().getId())
                    .nameUser(reviews.get(i).getUser().getName())
                    .reviews(reviews.get(i).getReviews())
                    .assessment(reviews.get(i).getAssessment())
                    .build());
        }


        return response;

    }

}
