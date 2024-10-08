package com.mikolajjanik.hospital_catering_admin.service;

import com.mikolajjanik.hospital_catering_admin.entity.Ward;
import java.util.Set;

public interface WardService {
    Ward findWardById(Long id);
    Set<Ward> findWardsByHospitalId(Long id);
    Set<Ward> findByHospitalIdAndNameContaining(Long id, String name);
}
