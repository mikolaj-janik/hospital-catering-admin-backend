package com.mikolajjanik.hospital_catering_admin.service;

import com.mikolajjanik.hospital_catering_admin.dao.PatientRepository;
import com.mikolajjanik.hospital_catering_admin.dao.WardRepository;
import com.mikolajjanik.hospital_catering_admin.entity.Patient;
import com.mikolajjanik.hospital_catering_admin.entity.Ward;
import com.mikolajjanik.hospital_catering_admin.exception.WardNotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private final WardRepository wardRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(WardRepository wardRepository, PatientRepository patientRepository) {
        this.wardRepository = wardRepository;
        this.patientRepository = patientRepository;
    }
    @Override
    @SneakyThrows
    public List<Patient> findPatientsByWardId(Long wardId) {
        Ward ward = wardRepository.findWardById(wardId);

        if (ward == null) {
            throw new WardNotFoundException(wardId);
        }
        return patientRepository.getPatientsByWardId(wardId);
    }
}
