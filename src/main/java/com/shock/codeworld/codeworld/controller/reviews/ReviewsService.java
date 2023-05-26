package com.shock.codeworld.codeworld.controller.reviews;

import com.shock.codeworld.codeworld.entity.Reviews;
import com.shock.codeworld.codeworld.entity.Role;
import com.shock.codeworld.codeworld.entity.User;
import com.shock.codeworld.codeworld.entity.UserData;
import com.shock.codeworld.codeworld.repository.ReviewsRepository;
import com.shock.codeworld.codeworld.repository.UserDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewsService {

    private final ReviewsRepository reviewsRepository;

    public ResponseReviews addReviews(ResponseReviews request) {

        Reviews reviews = Reviews.builder()
                .user(request.getUser())
                .farmer(request.getFarmer())
                .reviews(request.getReviews())
                .assessment(request.getAssessment())
                .build();

        reviewsRepository.save(reviews);

        return ResponseReviews.builder();
    }
}
