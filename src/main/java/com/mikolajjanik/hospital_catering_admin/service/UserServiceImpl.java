package com.mikolajjanik.hospital_catering_admin.service;

import com.mikolajjanik.hospital_catering_admin.dao.AdminRepository;
import com.mikolajjanik.hospital_catering_admin.dto.NewUserDTO;
import com.mikolajjanik.hospital_catering_admin.entity.Admin;
import com.mikolajjanik.hospital_catering_admin.exception.PasswordsNotMatchException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private AdminRepository adminRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Autowired
    public UserServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    @Override
    @SneakyThrows
    public Admin register(NewUserDTO user) {
        String password = user.getPassword();
        String repeatedPassword = user.getRepeatedPassword();

        if (!password.equals(repeatedPassword)) {
            throw new PasswordsNotMatchException();
        }

        Admin admin = new Admin();

        admin.setPassword(encoder.encode(password));

        admin.setEmail(user.getEmail());
        admin.setName(user.getName());
        admin.setSurname(user.getSurname());

        return adminRepository.save(admin);
    }
}
