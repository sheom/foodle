package com.sheom.foodle.controller;

import com.sheom.foodle.Exception.ReviewException;
import com.sheom.foodle.Exception.UserException;
import com.sheom.foodle.model.Review;
import com.sheom.foodle.model.User;
import com.sheom.foodle.request.ReviewRequest;
import com.sheom.foodle.service.ReviewSerive;
import com.sheom.foodle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewController {
	@Autowired
	private ReviewSerive reviewService;
	@Autowired
	private UserService userService;
	
	@PostMapping("/review")
	public ResponseEntity<Review> createReview(@RequestBody ReviewRequest review,@RequestHeader("Authorization") String jwt) throws UserException{
		User user =userService.findUserProfileByJwt(jwt);
		Review submitedReview = reviewService.submitReview(review,user);
		return ResponseEntity.ok(submitedReview);
	}

	    @PutMapping("/update/{reviewId}")
	    public ResponseEntity<Review> updateReview(@PathVariable Long reviewId, @RequestBody ReviewRequest updatedReview) throws ReviewException {
	        Review review = reviewService.updateReview(reviewId, updatedReview);
	        return new ResponseEntity<>(review, HttpStatus.OK);
	    }

	    @DeleteMapping("/delete/{reviewId}")
	    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) throws ReviewException {
	        reviewService.deleteReview(reviewId);
	        return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
	    }

	    @GetMapping("/average-rating")
	    public ResponseEntity<Double> calculateAverageRating(@RequestBody List<Review> reviews) {
	        double averageRating = reviewService.calculateAverageRating(reviews);
	        return new ResponseEntity<>(averageRating, HttpStatus.OK);
	    }
	}



