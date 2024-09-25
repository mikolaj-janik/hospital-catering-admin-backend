package com.mikolajjanik.hospital_catering_admin.service;

import com.mikolajjanik.hospital_catering_admin.dao.HospitalRepository;
import com.mikolajjanik.hospital_catering_admin.dto.HospitalDTO;
import com.mikolajjanik.hospital_catering_admin.entity.Hospital;
import com.mikolajjanik.hospital_catering_admin.exception.HospitalNotFoundException;
import lombok.SneakyThrows;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;

    @Autowired
    public HospitalServiceImpl(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public Page<Hospital> findAll(Pageable pageable) {
        return hospitalRepository.findAllBy(pageable);
    }

    @Override
    public Page<Hospital> findByNameContaining(String name, Pageable pageable) {
        return hospitalRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    @Override
    @SneakyThrows
    public Hospital findHospitalById(Long id) {
        Hospital hospital = hospitalRepository.findHospitalById(id);

        if (hospital == null) {
            throw new HospitalNotFoundException(id);
        }

        return hospital;
    }

    @Override
    @SneakyThrows
    public Hospital addHospital(HospitalDTO hospitalDTO) {
        Hospital hospital = new Hospital();

        hospital.setName(hospitalDTO.getName());
        hospital.setPhoneNumber(hospitalDTO.getPhoneNumber());
        hospital.setStreet(hospitalDTO.getStreet());
        hospital.setBuildingNo(hospitalDTO.getBuildingNo());
        hospital.setZipCode(hospitalDTO.getZipCode());
        hospital.setCity(hospitalDTO.getCity());

        return hospitalRepository.save(hospital);
    }
}
