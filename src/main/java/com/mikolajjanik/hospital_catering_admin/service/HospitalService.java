package com.mikolajjanik.hospital_catering_admin.service;

import com.mikolajjanik.hospital_catering_admin.dto.HospitalDTO;
import com.mikolajjanik.hospital_catering_admin.dto.NewHospitalDTO;
import org.springframework.data.domain.Pageable;
import com.mikolajjanik.hospital_catering_admin.entity.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface HospitalService {
    Page<HospitalDTO> findAll(Pageable pageable);
    Page<HospitalDTO> findByNameContaining(String name, Pageable pageable);
    HospitalDTO findHospitalById(Long id);
    HospitalDTO addHospital(NewHospitalDTO newHospitalDTO, MultipartFile picture);
}
