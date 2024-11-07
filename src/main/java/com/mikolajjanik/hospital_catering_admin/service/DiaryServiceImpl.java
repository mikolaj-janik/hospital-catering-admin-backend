package com.mikolajjanik.hospital_catering_admin.service;

import com.mikolajjanik.hospital_catering_admin.dao.DiaryRepository;
import com.mikolajjanik.hospital_catering_admin.dao.DietRepository;
import com.mikolajjanik.hospital_catering_admin.dao.MealRepository;
import com.mikolajjanik.hospital_catering_admin.dto.DiaryDTO;
import com.mikolajjanik.hospital_catering_admin.entity.Diary;
import com.mikolajjanik.hospital_catering_admin.entity.Diet;
import com.mikolajjanik.hospital_catering_admin.entity.Meal;
import com.mikolajjanik.hospital_catering_admin.exception.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class DiaryServiceImpl implements DiaryService {

    private final DiaryRepository diaryRepository;
    private final DietRepository dietRepository;
    private final MealRepository mealRepository;

    private static final String BREAKFAST_NAME = "śniadanie";
    private static final String LUNCH_NAME = "obiad";
    private static final String SUPPER_NAME = "kolacja";

    @Autowired
    public DiaryServiceImpl(
            DiaryRepository diaryRepository,
            DietRepository dietRepository,
            MealRepository mealRepository
    ) {
        this.diaryRepository = diaryRepository;
        this.dietRepository = dietRepository;
        this.mealRepository = mealRepository;
    }
    @Override
    @SneakyThrows
    public Diary getDiaryById(Long id) {
        checkIfDiaryExists(id);
        return this.diaryRepository.findDiaryById(id);
    }

    @Override
    @SneakyThrows
    public Diary createDiary(DiaryDTO diaryDTO) {
        Long dietId = diaryDTO.getDietId();
        Long breakfastId = diaryDTO.getBreakfastId();
        Long lunchId = diaryDTO.getLunchId();
        Long supperId = diaryDTO.getSupperId();
        String stringDate = diaryDTO.getDate();

        Diet diet = dietRepository.findDietById(dietId);
        Meal breakfast = mealRepository.findMealById(breakfastId);
        Meal lunch = mealRepository.findMealById(lunchId);
        Meal supper = mealRepository.findMealById(supperId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(stringDate, formatter);

        if (diet == null) {
            throw new DietNotFoundException(dietId);
        }

        if (breakfast == null) {
            throw new MealNotFoundException(breakfastId);
        }

        if (lunch == null) {
            throw new MealNotFoundException(lunchId);
        }

        if (supper == null) {
            throw new MealNotFoundException(supperId);
        }

        if (!dietId.equals(breakfast.getDiet().getId())) {
            throw new DietMismatchException(dietId, breakfastId);
        }

        if (!dietId.equals(lunch.getDiet().getId())) {
            throw new DietMismatchException(dietId, breakfastId);
        }

        if (!dietId.equals(supper.getDiet().getId())) {
            throw new DietMismatchException(dietId, breakfastId);
        }

        if (!breakfast.getType().equals(BREAKFAST_NAME)) {
            throw new IncorrectMealException(breakfastId, BREAKFAST_NAME);
        }

        if (!lunch.getType().equals(LUNCH_NAME)) {
            throw new IncorrectMealException(lunchId, LUNCH_NAME);
        }

        if (!supper.getType().equals(SUPPER_NAME)) {
            throw new IncorrectMealException(supperId, SUPPER_NAME);
        }

        if (breakfast.getPrice() > 0 || lunch.getPrice() > 0 || supper.getPrice() > 0) {
            throw new PremiumMealDiaryException();
        }

        Diary existingDiary = this.diaryRepository.findDiaryByDietIdAndDate(dietId, date);

        if (existingDiary != null) {
            throw new DiaryAlreadyExistsException(diet.getName(), date);
        }

        Diary diary = new Diary();

        diary.setDate(date);
        diary.setDiet(diet);
        diary.setBreakfast(breakfast);
        diary.setLunch(lunch);
        diary.setSupper(supper);

        return diaryRepository.save(diary);
    }

    private void checkIfDiaryExists(Long id) throws IOException {
        Diary diary = this.diaryRepository.findDiaryById(id);
        if (diary == null) {
            throw new DiaryNotFoundException(id);
        }
    }

}
