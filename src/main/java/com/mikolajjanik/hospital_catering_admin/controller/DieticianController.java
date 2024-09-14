package com.mikolajjanik.hospital_catering_admin.controller;

import com.mikolajjanik.hospital_catering_admin.entity.Dietician;
import com.mikolajjanik.hospital_catering_admin.service.DieticianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dieticians")
public class DieticianController {
    private final DieticianService dieticianService;

    @Autowired
    public DieticianController(DieticianService dieticianService) {
        this.dieticianService = dieticianService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dietician> findDieticianById(@PathVariable("id") Long id) {
        Dietician dietician = dieticianService.findDieticianById(id);
        return new ResponseEntity<>(dietician, HttpStatus.OK);
    }
}
