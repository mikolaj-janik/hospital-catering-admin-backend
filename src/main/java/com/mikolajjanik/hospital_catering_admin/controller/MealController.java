package com.mikolajjanik.hospital_catering_admin.controller;

import com.mikolajjanik.hospital_catering_admin.dto.MealDTO;
import com.mikolajjanik.hospital_catering_admin.dto.NewMealDTO;
import com.mikolajjanik.hospital_catering_admin.dto.UpdateMealDTO;
import com.mikolajjanik.hospital_catering_admin.entity.Meal;
import com.mikolajjanik.hospital_catering_admin.service.MealService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/meals")
public class MealController {
    private final MealService mealService;

    @Autowired
    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping("")
    public Page<MealDTO> getAll(Pageable pageable) {
        return mealService.findAll(pageable);
    }

    @GetMapping("/search")
    public Page<MealDTO> getMealsByName(@RequestParam("name") String name, Pageable pageable) {
        return mealService.findByNameContaining(name, pageable);
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<MealDTO> newMeal(@Valid @ModelAttribute NewMealDTO newMealDTO,
                                           @RequestPart("picture") MultipartFile picture) {
        MealDTO meal = mealService.addMeal(newMealDTO, picture);
        return new ResponseEntity<>(meal, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @ResponseBody
    public ResponseEntity<MealDTO> updateMeal(@Valid @ModelAttribute UpdateMealDTO mealDTO,
                                              @RequestPart("picture") MultipartFile picture) {
        MealDTO meal = mealService.updateMeal(mealDTO, picture);
        return new ResponseEntity<>(meal, HttpStatus.OK);
    }
}
