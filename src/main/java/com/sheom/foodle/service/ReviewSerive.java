package com.sheom.foodle.service;

import com.sheom.foodle.Exception.ReviewException;
import com.sheom.foodle.model.Review;
import com.sheom.foodle.model.User;
import com.sheom.foodle.request.ReviewRequest;

import java.util.List;

public interface ReviewSerive {
	
    public Review submitReview(ReviewRequest review,User user);
    public void deleteReview(Long reviewId) throws ReviewException;
    public double calculateAverageRating(List<Review> reviews);
	public Review updateReview(Long reviewId, ReviewRequest updatedReview) throws ReviewException;

}
