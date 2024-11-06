package com.microservices.ReviewMs.review;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews(int companyId);

    boolean addReview(int companyId, Review review);
}
