package com.sheom.foodle.repository;

import com.sheom.foodle.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
