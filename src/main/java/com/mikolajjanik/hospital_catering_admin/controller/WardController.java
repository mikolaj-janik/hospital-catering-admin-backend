package com.mikolajjanik.hospital_catering_admin.controller;

import com.mikolajjanik.hospital_catering_admin.entity.Dietician;
import com.mikolajjanik.hospital_catering_admin.entity.Ward;
import com.mikolajjanik.hospital_catering_admin.service.DieticianService;
import com.mikolajjanik.hospital_catering_admin.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/wards")
public class WardController {
    private final WardService wardService;
    private final DieticianService dieticianService;

    @Autowired
    public WardController(WardService wardService, DieticianService dieticianService) {
        this.wardService = wardService;
        this.dieticianService = dieticianService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ward> getWardById(@PathVariable("id") Long id) {
        Ward ward = wardService.findWardById(id);
        return new ResponseEntity<>(ward, HttpStatus.OK);
    }

    @GetMapping("/{id}/dieticians")
    public ResponseEntity<Set<Dietician>> getDieticiansByWardId(@PathVariable("id") Long id) {
        Set<Dietician> dieticians = dieticianService.findDieticiansByWardId(id);
        return new ResponseEntity<>(dieticians, HttpStatus.OK);
    }
}
