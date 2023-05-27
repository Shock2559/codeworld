package com.shock.codeworld.codeworld.controller.reviews;

import com.shock.codeworld.codeworld.controller.user.ResponseUserData;
import com.shock.codeworld.codeworld.controller.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewsController {

    private final ReviewsService service;

    @CrossOrigin
    @PostMapping("/add-reviews")
    public ResponseReviews addReviews(@RequestBody ResponseReviews request){
        return service.addReviews(request);
    }

    @CrossOrigin
    @GetMapping("/reviews-by-id")
    public List<ResponseReviews> getReviewById(@RequestParam Integer id) {
        return service.getReviewById(id);
    }

    @CrossOrigin
    @GetMapping("/all-reviews")
    public List<ResponseReviews> getAllReview() {
        return service.getAllReviews();
    }

}
