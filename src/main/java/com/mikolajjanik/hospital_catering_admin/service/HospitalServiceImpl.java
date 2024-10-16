package com.mikolajjanik.hospital_catering_admin.service;

import com.mikolajjanik.hospital_catering_admin.dao.HospitalRepository;
import com.mikolajjanik.hospital_catering_admin.dto.HospitalDTO;
import com.mikolajjanik.hospital_catering_admin.dto.NewHospitalDTO;
import com.mikolajjanik.hospital_catering_admin.entity.Hospital;
import com.mikolajjanik.hospital_catering_admin.exception.HospitalNotFoundException;
import lombok.SneakyThrows;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;

    @Autowired
    public HospitalServiceImpl(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public Page<HospitalDTO> findAll(Pageable pageable) {
        Page<Hospital> hospitals = hospitalRepository.findAll(pageable);
        List<HospitalDTO> hospitalsList = new ArrayList<>();

        for(Hospital hospital : hospitals.getContent()) {

            byte[] rawPicture = hospitalRepository.findPictureById(hospital.getId());
            HospitalDTO hospitalDTO = new HospitalDTO(
                    hospital.getId(),
                    hospital.getName(),
                    hospital.getPhoneNumber(),
                    hospital.getStreet(),
                    hospital.getBuildingNo(),
                    hospital.getZipCode(),
                    hospital.getCity(),
                    Base64.getEncoder().encodeToString(rawPicture)
            );

            hospitalsList.add(hospitalDTO);
        }

        return new PageImpl<>(hospitalsList, pageable, hospitalsList.size());
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
    public Hospital addHospital(NewHospitalDTO newHospitalDTO) {
        Hospital hospital = new Hospital();

        hospital.setName(newHospitalDTO.getName());
        hospital.setPhoneNumber(newHospitalDTO.getPhoneNumber());
        hospital.setStreet(newHospitalDTO.getStreet());
        hospital.setBuildingNo(newHospitalDTO.getBuildingNo());
        hospital.setZipCode(newHospitalDTO.getZipCode());
        hospital.setCity(newHospitalDTO.getCity());

        return hospitalRepository.save(hospital);
    }
}
