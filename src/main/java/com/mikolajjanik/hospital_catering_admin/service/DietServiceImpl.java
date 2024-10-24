package com.mikolajjanik.hospital_catering_admin.service;

import com.mikolajjanik.hospital_catering_admin.dao.DietRepository;
import com.mikolajjanik.hospital_catering_admin.dto.DietDTO;
import com.mikolajjanik.hospital_catering_admin.entity.Diet;
import com.mikolajjanik.hospital_catering_admin.exception.DietAlreadyExistException;
import com.mikolajjanik.hospital_catering_admin.exception.DietNotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class DietServiceImpl implements DietService {
    private final DietRepository dietRepository;

    @Autowired
    public DietServiceImpl(DietRepository dietRepository) {
        this.dietRepository = dietRepository;
    }

    @Override
    public Set<Diet> findAll() {
        return dietRepository.findAllBy();
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

        Diet diet = dietRepository.findDietByName(name);

        if (diet != null) {
            throw new DietAlreadyExistException();
        }
        return dietRepository.save(new Diet(name, description));
    }
}
