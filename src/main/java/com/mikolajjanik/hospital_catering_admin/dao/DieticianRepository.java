package com.mikolajjanik.hospital_catering_admin.dao;

import com.mikolajjanik.hospital_catering_admin.entity.Dietician;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface DieticianRepository extends JpaRepository<Dietician, Long> {
    Dietician findDieticianById(Long id);
    Set<Dietician> findDieticiansByHospitalId(Long id);
}
