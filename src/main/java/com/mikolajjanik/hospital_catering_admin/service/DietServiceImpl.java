package com.mikolajjanik.hospital_catering_admin.service;

import com.mikolajjanik.hospital_catering_admin.dao.DietRepository;
import com.mikolajjanik.hospital_catering_admin.entity.Diet;
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
}
