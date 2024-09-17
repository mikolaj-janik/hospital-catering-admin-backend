package com.mikolajjanik.hospital_catering_admin.controller;

import com.mikolajjanik.hospital_catering_admin.entity.Diet;
import com.mikolajjanik.hospital_catering_admin.entity.Meal;
import com.mikolajjanik.hospital_catering_admin.service.DietService;
import com.mikolajjanik.hospital_catering_admin.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/diets")
public class DietController {
    private final DietService dietService;
    private final MealService mealService;

    @Autowired
    public DietController(DietService dietService, MealService mealService) {
        this.dietService = dietService;
        this.mealService = mealService;
    }

    @GetMapping("")
    public ResponseEntity<Set<Diet>> getAll() {
        Set<Diet> diets = dietService.findAll();
        return new ResponseEntity<>(diets, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diet> getDietById(@PathVariable("id") Long id) {
        Diet diet = dietService.findDietById(id);
        return new ResponseEntity<>(diet, HttpStatus.OK);
    }

    @GetMapping("/{id}/meals")
    public Page<Meal> getMealsByDietId(@PathVariable("id") Long id, Pageable pageable) {
        return mealService.findMealsByDietId(id, pageable);
    }

    @GetMapping("/{id}/meals/{name}")
    public Page<Meal> getMealsByDietAndByName(@PathVariable("id") Long id,
                                              @PathVariable("name") String name,
                                              Pageable pageable) {
        return mealService.findMealsByDietIdAndName(id, name, pageable);
    }
}
