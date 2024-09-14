package com.mikolajjanik.hospital_catering_admin.service;

import com.mikolajjanik.hospital_catering_admin.dao.HospitalRepository;
import com.mikolajjanik.hospital_catering_admin.dao.WardRepository;
import com.mikolajjanik.hospital_catering_admin.entity.Hospital;
import com.mikolajjanik.hospital_catering_admin.entity.Ward;
import com.mikolajjanik.hospital_catering_admin.exception.HospitalNotFoundException;
import com.mikolajjanik.hospital_catering_admin.exception.WardNotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class WardServiceImpl implements WardService {

    private final WardRepository wardRepository;

    private final HospitalRepository hospitalRepository;

    @Autowired
    public WardServiceImpl(WardRepository wardRepository, HospitalRepository hospitalRepository) {
        this.wardRepository = wardRepository;
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    @SneakyThrows
    public Ward findWardById(Long id) {
        Ward ward = wardRepository.findWardById(id);

        if (ward == null) {
            throw new WardNotFoundException(id);
        }

        return ward;
    }

    @Override
    @SneakyThrows
    public Set<Ward> findWardsByHospitalId(Long id) {
        Hospital hospital = hospitalRepository.findHospitalById(id);

        if (hospital == null) {
            throw new HospitalNotFoundException(id);
        }

        return wardRepository.findWardsByHospitalId(id);
    }

    @Override
    @SneakyThrows
    public Set<Ward> findByHospitalIdAndNameContaining(Long id, String name) {
        Hospital hospital = hospitalRepository.findHospitalById(id);

        if (hospital == null) {
            throw new HospitalNotFoundException(id);
        }

        return wardRepository.findWardsByHospitalIdAndNameContainingIgnoreCase(id, name);
    }
}
