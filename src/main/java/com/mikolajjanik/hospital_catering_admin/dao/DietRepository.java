package com.mikolajjanik.hospital_catering_admin.dao;

import com.mikolajjanik.hospital_catering_admin.entity.Diet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface DietRepository extends JpaRepository<Diet, Long> {
    Set<Diet> findAllBy();
    Diet findDietByName(String name);
    Set<Diet> findDietsByNameContainingIgnoreCase(String name);
    Diet findDietById(Long id);
}
