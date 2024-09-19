package com.mikolajjanik.hospital_catering_admin.service;

import com.mikolajjanik.hospital_catering_admin.dto.LoginUserDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {
    String generateToken(String email);

    String extractUserName(String token);

    boolean validateToken(String token, UserDetails userDetails);
}
