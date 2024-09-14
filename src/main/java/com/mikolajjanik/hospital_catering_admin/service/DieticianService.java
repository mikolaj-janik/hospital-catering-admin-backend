package com.mikolajjanik.hospital_catering_admin.service;

import com.mikolajjanik.hospital_catering_admin.entity.Dietician;

import java.util.Set;

public interface DieticianService {
    Dietician findDieticianById(Long id);
    Set<Dietician> findDieticiansByHospitalId(Long id);
    Set<Dietician> findDieticiansByWardId(Long id);
}
