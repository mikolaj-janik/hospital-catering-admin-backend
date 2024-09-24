package com.mikolajjanik.hospital_catering_admin.service;

import com.mikolajjanik.hospital_catering_admin.dto.HospitalDTO;
import org.springframework.data.domain.Pageable;
import com.mikolajjanik.hospital_catering_admin.entity.Hospital;
import org.springframework.data.domain.Page;

public interface HospitalService {
    Page<Hospital> findAll(Pageable pageable);
    Page<Hospital> findByNameContaining(String name, Pageable pageable);
    Hospital findHospitalById(Long id);
    Hospital addHospital(HospitalDTO hospitalDTO);
}
