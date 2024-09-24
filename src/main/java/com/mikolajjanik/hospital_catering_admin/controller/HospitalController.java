package com.mikolajjanik.hospital_catering_admin.controller;

import com.mikolajjanik.hospital_catering_admin.dto.HospitalDTO;
import com.mikolajjanik.hospital_catering_admin.entity.Dietician;
import com.mikolajjanik.hospital_catering_admin.entity.Hospital;
import com.mikolajjanik.hospital_catering_admin.entity.Ward;
import com.mikolajjanik.hospital_catering_admin.service.DieticianService;
import com.mikolajjanik.hospital_catering_admin.service.WardService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import com.mikolajjanik.hospital_catering_admin.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.Stack;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {
    private final HospitalService hospitalService;
    private final WardService wardService;
    private final DieticianService dieticianService;

    @Autowired
    public HospitalController(HospitalService hospitalService, WardService wardService, DieticianService dieticianService) {
        this.hospitalService = hospitalService;
        this.wardService = wardService;
        this.dieticianService = dieticianService;
    }

    @GetMapping("")
    public Page<Hospital> getAll(Pageable pageable) {
        return hospitalService.findAll(pageable);
    }

    @GetMapping("/search/{name}")
    public Page<Hospital> getHospitalsByName(@PathVariable("name") String name, Pageable pageable) {
        return hospitalService.findByNameContaining(name, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hospital> getHospitalById(@PathVariable("id") Long id) {
        Hospital hospital = hospitalService.findHospitalById(id);
        return new ResponseEntity<>(hospital, HttpStatus.OK);
    }

    @GetMapping("/{id}/wards")
    public ResponseEntity<Set<Ward>> getWardsByHospitalId(@PathVariable("id") Long id) {
        Set<Ward> wards = wardService.findWardsByHospitalId(id);
        return new ResponseEntity<>(wards, HttpStatus.OK);
    }

    @GetMapping("/{id}/wards/{name}")
    public ResponseEntity<Set<Ward>> getWardsByName(@PathVariable("id") Long id, @PathVariable("name") String name) {
        Set<Ward> wards = wardService.findByHospitalIdAndNameContaining(id, name);
        return new ResponseEntity<>(wards, HttpStatus.OK);
    }

    @GetMapping("/{id}/dieticians")
    public ResponseEntity<Set<Dietician>> getDieticiansByHospitalId(@PathVariable("id") Long id) {
        Set<Dietician> dieticians = dieticianService.findDieticiansByHospitalId(id);
        return new ResponseEntity<>(dieticians, HttpStatus.OK);
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<Hospital> newHospital(@Valid @RequestBody HospitalDTO hospitalDTO) {
        Hospital hospital = new Hospital();

        return new ResponseEntity<>(hospital, HttpStatus.CREATED);
    }

}
