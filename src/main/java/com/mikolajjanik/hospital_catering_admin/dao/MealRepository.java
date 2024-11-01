package com.mikolajjanik.hospital_catering_admin.dao;

import com.mikolajjanik.hospital_catering_admin.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MealRepository extends JpaRepository<Meal, Long> {
    Meal findMealById(Long id);
    Page<Meal> findMealsByDietId(Long id, Pageable pageable);
    Page<Meal> findMealsByDietIdAndNameContainingIgnoreCase(Long id, String name, Pageable pageable);

    @Query(value = "SELECT obraz FROM posiłek WHERE id = :id", nativeQuery = true)
    byte[] findPictureById(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE posiłek SET obraz = :picture WHERE id = :id", nativeQuery = true)
    void updatePictureByMealId(@Param("id") Long id, @Param("picture") byte[] picture);

}
