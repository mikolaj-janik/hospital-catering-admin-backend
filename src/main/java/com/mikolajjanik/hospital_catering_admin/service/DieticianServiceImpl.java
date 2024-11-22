package com.mikolajjanik.hospital_catering_admin.service;

import com.mikolajjanik.hospital_catering_admin.dao.DieticianRepository;
import com.mikolajjanik.hospital_catering_admin.dao.HospitalRepository;
import com.mikolajjanik.hospital_catering_admin.dao.WardRepository;
import com.mikolajjanik.hospital_catering_admin.entity.Dietician;
import com.mikolajjanik.hospital_catering_admin.entity.Hospital;
import com.mikolajjanik.hospital_catering_admin.entity.Ward;
import com.mikolajjanik.hospital_catering_admin.exception.DieticianNotFoundException;
import com.mikolajjanik.hospital_catering_admin.exception.HospitalNotFoundException;
import com.mikolajjanik.hospital_catering_admin.exception.WardNotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class DieticianServiceImpl implements DieticianService {

    private final DieticianRepository dieticianRepository;
    private final HospitalRepository hospitalRepository;
    private final WardRepository wardRepository;

    @Autowired
    public DieticianServiceImpl(DieticianRepository dieticianRepository, HospitalRepository hospitalRepository, WardRepository wardRepository) {
        this.dieticianRepository = dieticianRepository;
        this.hospitalRepository = hospitalRepository;
        this.wardRepository = wardRepository;
    }

    @Override
    @SneakyThrows
    public Dietician findDieticianById(Long id) {
        Dietician dietician = dieticianRepository.findDieticianById(id);

        if (dietician == null) {
            throw new DieticianNotFoundException(id);
        }
        return dietician;
    }

    @Override
    @SneakyThrows
    public Set<Dietician> findDieticiansByHospitalId(Long id) {
        Hospital hospital = hospitalRepository.findHospitalById(id);

        if (hospital == null) {
            throw new HospitalNotFoundException(id);
        }

        return dieticianRepository.findDieticiansByHospitalId(id);
    }

    @Override
    @SneakyThrows
    public Set<Dietician> findDieticiansByWardId(Long id) {
        Ward ward = wardRepository.findWardById(id);

        if (ward == null) {
            throw new WardNotFoundException(id);
        }

        return ward.getDieticians();
    }

    @Override
    @SneakyThrows
    public List<Dietician> findAllDieticians(Long hospitalId) {
        List<Dietician> dieticians;
        if (hospitalId == 0) {
            dieticians = dieticianRepository.findAllDieticians();

        } else {
            Hospital hospital = hospitalRepository.findHospitalById(hospitalId);

            if (hospital == null) {
                throw new HospitalNotFoundException(hospitalId);
            }
            dieticians = dieticianRepository.findDieticians(hospitalId);
        }

        return dieticians;
    }
}
