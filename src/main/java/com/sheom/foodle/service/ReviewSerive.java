package com.sheom.foodle.service;

import com.zosh.Exception.ReviewException;
import com.zosh.model.Review;
import com.zosh.model.User;
import com.zosh.request.ReviewRequest;

import java.util.List;

public interface ReviewSerive {
	
    public Review submitReview(ReviewRequest review,User user);
    public void deleteReview(Long reviewId) throws ReviewException;
    public double calculateAverageRating(List<Review> reviews);
	public Review updateReview(Long reviewId, ReviewRequest updatedReview) throws ReviewException;

}
