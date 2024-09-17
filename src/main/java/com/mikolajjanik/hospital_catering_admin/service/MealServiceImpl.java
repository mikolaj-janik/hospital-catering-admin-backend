package com.mikolajjanik.hospital_catering_admin.service;

import com.mikolajjanik.hospital_catering_admin.dao.DietRepository;
import com.mikolajjanik.hospital_catering_admin.dao.MealRepository;
import com.mikolajjanik.hospital_catering_admin.entity.Diet;
import com.mikolajjanik.hospital_catering_admin.entity.Meal;
import com.mikolajjanik.hospital_catering_admin.exception.DietNotFoundException;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MealServiceImpl implements MealService {
    private final MealRepository mealRepository;
    private final DietRepository dietRepository;

    public MealServiceImpl(MealRepository mealRepository, DietRepository dietRepository) {
        this.mealRepository = mealRepository;
        this.dietRepository = dietRepository;
    }

    @Override
    @SneakyThrows
    public Page<Meal> findMealsByDietId(Long id, Pageable pageable) {
        Diet diet = dietRepository.findDietById(id);

        if (diet == null) {
            throw new DietNotFoundException(id);
        }

        return mealRepository.findMealsByDietId(id, pageable);
    }

    @Override
    @SneakyThrows
    public Page<Meal> findMealsByDietIdAndName(Long id, String name, Pageable pageable) {
        Diet diet = dietRepository.findDietById(id);

        if (diet == null) {
            throw new DietNotFoundException(id);
        }

        return mealRepository.findMealsByDietIdAndNameContainingIgnoreCase(id, name, pageable);
    }
}
