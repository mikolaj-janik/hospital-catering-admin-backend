package com.mikolajjanik.hospital_catering_admin.dao;

import com.mikolajjanik.hospital_catering_admin.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface MealRepository extends JpaRepository<Meal, Long> {
    Page<Meal> findMealsByDietId(Long id, Pageable pageable);
    Page<Meal> findMealsByDietIdAndNameContainingIgnoreCase(Long id, String name, Pageable pageable);
}
