package com.microservices.ReviewMs.review.impl;

import com.microservices.ReviewMs.review.Review;
import com.microservices.ReviewMs.review.ReviewRepository;
import com.microservices.ReviewMs.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    //One connect with other repo by its service class only. Cant directly access other repositories.
    ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews(int companyId) {

        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }


    @Override
    public boolean addReview(int companyId, Review review) {

        if(companyId > 0 && review != null) {
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }
}
