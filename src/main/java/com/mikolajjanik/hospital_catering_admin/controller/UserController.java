package com.mikolajjanik.hospital_catering_admin.controller;

import com.mikolajjanik.hospital_catering_admin.dto.LoginUserDTO;
import com.mikolajjanik.hospital_catering_admin.dto.NewUserDTO;
import com.mikolajjanik.hospital_catering_admin.dto.TokenDTO;
import com.mikolajjanik.hospital_catering_admin.entity.Admin;
import com.mikolajjanik.hospital_catering_admin.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseEntity<Admin> register(@Valid @RequestBody NewUserDTO user) {
        Admin admin = userService.register(user);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@Valid @RequestBody LoginUserDTO user) {
        TokenDTO tokenDTO = new TokenDTO();
        String token = userService.verify(user);
        tokenDTO.setToken(token);
        return new ResponseEntity<>(tokenDTO, HttpStatus.OK);
    }
}
