package com.sheom.foodle.repository;

import com.sheom.foodle.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
