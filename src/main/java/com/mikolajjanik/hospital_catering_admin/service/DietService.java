package com.mikolajjanik.hospital_catering_admin.service;

import com.mikolajjanik.hospital_catering_admin.dto.DietDTO;
import com.mikolajjanik.hospital_catering_admin.dto.UpdateDietDTO;
import com.mikolajjanik.hospital_catering_admin.entity.Diet;

import java.util.Set;

public interface DietService {
    Set<Diet> findAll();
    Diet findDietById(Long id);
    Diet addDiet(DietDTO dietDTO);
    Diet updateDiet(UpdateDietDTO dietDTO);
    Set<Diet> findDietsByName(String name);
}
