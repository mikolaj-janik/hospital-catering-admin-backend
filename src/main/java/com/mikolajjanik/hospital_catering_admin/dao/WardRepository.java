package com.mikolajjanik.hospital_catering_admin.dao;

import com.mikolajjanik.hospital_catering_admin.entity.Ward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;


public interface WardRepository extends JpaRepository<Ward, Long> {
    Ward findWardById(Long id);
    Set<Ward> findWardsByHospitalId(Long id);
    Set<Ward> findWardsByHospitalIdAndNameContainingIgnoreCase(Long id, String name);
}
