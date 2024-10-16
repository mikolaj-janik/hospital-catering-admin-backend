package com.mikolajjanik.hospital_catering_admin.service;

import com.mikolajjanik.hospital_catering_admin.dto.HospitalDTO;
import com.mikolajjanik.hospital_catering_admin.dto.NewHospitalDTO;
import org.springframework.data.domain.Pageable;
import com.mikolajjanik.hospital_catering_admin.entity.Hospital;
import org.springframework.data.domain.Page;

public interface HospitalService {
    Page<HospitalDTO> findAll(Pageable pageable);
    Page<Hospital> findByNameContaining(String name, Pageable pageable);
    Hospital findHospitalById(Long id);
    Hospital addHospital(NewHospitalDTO newHospitalDTO);
}
