package com.mikolajjanik.hospital_catering_admin.service;

import com.mikolajjanik.hospital_catering_admin.dao.DietRepository;
import com.mikolajjanik.hospital_catering_admin.dao.MealRepository;
import com.mikolajjanik.hospital_catering_admin.dto.MealDTO;
import com.mikolajjanik.hospital_catering_admin.dto.NewMealDTO;
import com.mikolajjanik.hospital_catering_admin.dto.UpdateMealDTO;
import com.mikolajjanik.hospital_catering_admin.entity.Diet;
import com.mikolajjanik.hospital_catering_admin.entity.Meal;
import com.mikolajjanik.hospital_catering_admin.exception.DietNotFoundException;
import com.mikolajjanik.hospital_catering_admin.exception.MealNotFoundException;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class MealServiceImpl implements MealService {
    private final MealRepository mealRepository;
    private final DietRepository dietRepository;

    public MealServiceImpl(MealRepository mealRepository, DietRepository dietRepository) {
        this.mealRepository = mealRepository;
        this.dietRepository = dietRepository;
    }

    @Override
    public Page<MealDTO> findAll(Pageable pageable) {
        Page<Meal> meals = mealRepository.findAll(pageable);
        return handleFindMeals(meals, pageable);
    }

    @Override
    @SneakyThrows
    public Page<MealDTO> findMealsByDietId(Long id, Pageable pageable) {
        Diet diet = dietRepository.findDietById(id);

        if (diet == null) {
            throw new DietNotFoundException(id);
        }

        Page<Meal> meals = mealRepository.findMealsByDietId(id, pageable);

        return handleFindMeals(meals, pageable);
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

    @Override
    @SneakyThrows
    public MealDTO addMeal(NewMealDTO newMealDTO, MultipartFile picture) {
        Meal meal = new Meal();

        HospitalServiceImpl.validateImageFormat(picture);

        Long dietId = Long.parseLong(newMealDTO.getDietId());
        Diet diet = dietRepository.findDietById(dietId);

        if (diet == null) {
            throw new DietNotFoundException(dietId);
        }

        meal.setDiet(diet);
        meal.setName(newMealDTO.getName());
        meal.setDescription(newMealDTO.getDescription());
        meal.setPrice(Double.parseDouble(newMealDTO.getPrice()));
        meal.setType(newMealDTO.getType());
        meal.setCalories(Double.parseDouble(newMealDTO.getCalories()));
        meal.setProtein(Double.parseDouble(newMealDTO.getProtein()));
        meal.setCarbohydrates(Double.parseDouble(newMealDTO.getCarbohydrates()));
        meal.setFats(Double.parseDouble(newMealDTO.getFats()));

        meal = mealRepository.save(meal);

        byte[] pictureByte = picture.getBytes();

        mealRepository.updatePictureByMealId(meal.getId(), pictureByte);

        return new MealDTO(
                meal.getId(),
                meal.getDiet(),
                meal.getName(),
                meal.getDescription(),
                meal.getPrice(),
                meal.getType().toLowerCase(),
                meal.getCalories(),
                meal.getProtein(),
                meal.getCarbohydrates(),
                meal.getFats(),
                Base64.getEncoder().encodeToString(pictureByte)
        );
    }

    @Override
    @SneakyThrows
    public MealDTO updateMeal(UpdateMealDTO mealDTO, MultipartFile picture) {
        Long id = Long.parseLong(mealDTO.getId());

        Long dietId = Long.parseLong(mealDTO.getDietId());
        Diet diet = dietRepository.findDietById(dietId);

        if (diet == null) {
            throw new DietNotFoundException(dietId);
        }

        Meal meal = mealRepository.findMealById(id);

        if (meal == null) {
            throw new MealNotFoundException(id);
        }

        byte[] pictureByte = picture.getBytes();

        if (picture.isEmpty()) {
            pictureByte = mealRepository.findPictureById(id);
        }

        meal.setDiet(diet);
        meal.setName(mealDTO.getName());
        meal.setDescription(mealDTO.getDescription());
        meal.setPrice(Double.parseDouble(mealDTO.getPrice()));
        meal.setType(mealDTO.getType());
        meal.setCalories(Double.parseDouble(mealDTO.getCalories()));
        meal.setProtein(Double.parseDouble(mealDTO.getProtein()));
        meal.setCarbohydrates(Double.parseDouble(mealDTO.getCarbohydrates()));
        meal.setFats(Double.parseDouble(mealDTO.getFats()));

        meal = mealRepository.save(meal);
        mealRepository.updatePictureByMealId(meal.getId(), pictureByte);

        return new MealDTO(
                meal.getId(),
                meal.getDiet(),
                meal.getName(),
                meal.getDescription(),
                meal.getPrice(),
                meal.getType().toLowerCase(),
                meal.getCalories(),
                meal.getProtein(),
                meal.getCarbohydrates(),
                meal.getFats(),
                Base64.getEncoder().encodeToString(pictureByte)
        );
    }

    private Page<MealDTO> handleFindMeals(Page<Meal> meals, Pageable pageable) {
        List<MealDTO> mealsList = new ArrayList<>();

        for (Meal meal : meals.getContent()) {
            byte[] rawPicture = mealRepository.findPictureById(meal.getId());
            MealDTO mealDTO = new MealDTO(
                    meal.getId(),
                    meal.getDiet(),
                    meal.getName(),
                    meal.getDescription(),
                    meal.getPrice(),
                    meal.getType(),
                    meal.getCalories(),
                    meal.getProtein(),
                    meal.getCarbohydrates(),
                    meal.getFats(),
                    Base64.getEncoder().encodeToString(rawPicture)
            );

            mealsList.add(mealDTO);
        }
        return new PageImpl<>(mealsList, pageable, meals.getTotalElements());
    }
}
