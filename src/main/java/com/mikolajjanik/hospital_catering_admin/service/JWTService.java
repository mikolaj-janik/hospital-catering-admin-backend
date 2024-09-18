package com.mikolajjanik.hospital_catering_admin.service;

import com.mikolajjanik.hospital_catering_admin.dto.LoginUserDTO;

public interface JWTService {
    String generateToken(String email);
}
