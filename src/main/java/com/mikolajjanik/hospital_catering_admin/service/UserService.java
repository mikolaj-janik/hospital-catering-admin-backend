package com.mikolajjanik.hospital_catering_admin.service;

import com.mikolajjanik.hospital_catering_admin.dto.NewUserDTO;
import com.mikolajjanik.hospital_catering_admin.entity.Admin;

public interface UserService {
    Admin register(NewUserDTO userDTO);
}