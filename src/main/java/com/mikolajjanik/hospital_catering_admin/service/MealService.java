package com.mikolajjanik.hospital_catering_admin.service;

import com.mikolajjanik.hospital_catering_admin.dto.MealDTO;
import com.mikolajjanik.hospital_catering_admin.dto.NewMealDTO;
import com.mikolajjanik.hospital_catering_admin.dto.UpdateMealDTO;
import com.mikolajjanik.hospital_catering_admin.entity.Meal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface MealService {
    Page<MealDTO> findAll(Pageable pageable);
    Page<MealDTO> findMealsByDietId(Long id, Pageable pageable);
    Page<Meal> findMealsByDietIdAndName(Long id, String name, Pageable pageable);
    MealDTO addMeal(NewMealDTO newMealDTO, MultipartFile picture);
    MealDTO updateMeal(UpdateMealDTO mealDTO, MultipartFile picture);
}
