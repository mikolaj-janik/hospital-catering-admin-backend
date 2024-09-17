package com.mikolajjanik.hospital_catering_admin.dto;

import lombok.Data;
@Data
public class NewUserDTO {

    private String email;

    private String password;

    private String repeatedPassword;

    private String name;

    private String surname;

}
