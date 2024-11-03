package com.mikolajjanik.hospital_catering_admin.service;

import com.mikolajjanik.hospital_catering_admin.dao.DietRepository;
import com.mikolajjanik.hospital_catering_admin.dao.MealRepository;
import com.mikolajjanik.hospital_catering_admin.dto.DietDTO;
import com.mikolajjanik.hospital_catering_admin.dto.UpdateDietDTO;
import com.mikolajjanik.hospital_catering_admin.entity.Diet;
import com.mikolajjanik.hospital_catering_admin.entity.Meal;
import com.mikolajjanik.hospital_catering_admin.exception.DietAlreadyExistException;
import com.mikolajjanik.hospital_catering_admin.exception.DietNotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DietServiceImpl implements DietService {
    private final DietRepository dietRepository;

    private final MealRepository mealRepository;

    @Autowired
    public DietServiceImpl(DietRepository dietRepository, MealRepository mealRepository) {
        this.dietRepository = dietRepository;
        this.mealRepository = mealRepository;
    }

    @Override
    public Set<Diet> findAll() {
        return dietRepository.findAllByOrderByName();
    }

    @Override
    public Set<Diet> findAllCurrentDiets() {
        Set<Diet> diets = dietRepository.findAllByOrderByName();
        Set<Diet> dietsToReturn = new HashSet<>();

        for (Diet diet : diets) {
            Set<Meal> meals = mealRepository.findMealsByDietId(diet.getId());
            if (!meals.isEmpty()) {
                dietsToReturn.add(diet);
            }
        }
        return dietsToReturn;
    }

    @Override
    @SneakyThrows
    public Diet findDietById(Long id) {
        Diet diet = dietRepository.findDietById(id);

        if (diet == null) {
            throw new DietNotFoundException(id);
        }

        return diet;
    }

    @Override
    @SneakyThrows
    public Diet addDiet(DietDTO dietDTO) {

        String name = dietDTO.getName();
        String description = dietDTO.getDescription();

        name = name.toLowerCase();

        checkDietAlreadyExists(name);

        name = name.toLowerCase();
        
        return dietRepository.save(new Diet(name, description));
    }

    @Override
    @SneakyThrows
    public Diet updateDiet(UpdateDietDTO dietDTO) {
        Long id = dietDTO.getId();
        String name = dietDTO.getName();
        String description = dietDTO.getDescription();

        name = name.toLowerCase();

        checkDietAlreadyExists(id);

        Diet diet = dietRepository.findDietById(id);
        diet.setName(name);
        diet.setDescription(description);

        return dietRepository.save(diet);

    }

    @Override
    public Set<Diet> findDietsByName(String name) {
        return dietRepository.findDietsByNameContainingIgnoreCase(name);
    }

    private void checkDietAlreadyExists(String name) throws IOException {
        Diet diet = dietRepository.findDietByName(name);

        if (diet != null) {
            throw new DietAlreadyExistException();
        }
    }

    private void checkDietAlreadyExists(Long id) throws IOException {
        Diet diet = dietRepository.findDietById(id);

        if (diet == null) {
            throw new DietNotFoundException(id);
        }
    }
}
