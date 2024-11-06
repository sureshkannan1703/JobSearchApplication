package com.microservices.ReviewMs.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviewsOnCompany(@RequestParam(name = "companyId") int companyId) {

        List<Review> reviews = reviewService.getAllReviews(companyId);
        if(reviews.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Review> addReviewOnCompany(@RequestParam int companyId, @RequestBody Review review) {

       if(reviewService.addReview(companyId,review)){
           return new ResponseEntity<>(review, HttpStatus.CREATED);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
