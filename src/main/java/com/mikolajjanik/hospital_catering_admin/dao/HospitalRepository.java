package com.mikolajjanik.hospital_catering_admin.dao;

import com.mikolajjanik.hospital_catering_admin.entity.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    Page<Hospital> findAllBy(Pageable pageable);
    Page<Hospital> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Hospital findHospitalById(Long id);
}
