package com.mikolajjanik.hospital_catering_admin.service;

import com.mikolajjanik.hospital_catering_admin.entity.Meal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MealService {
    Page<Meal> findMealsByDietId(Long id, Pageable pageable);
}
