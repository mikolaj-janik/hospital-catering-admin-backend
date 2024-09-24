package com.mikolajjanik.hospital_catering_admin.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class NewUserDTO {

    @NotNull(message = "Email address cannot be null.")
    @NotBlank(message = "Email address cannot be blank.")
    @Email(message = "Provided string is not an email.")
    private String email;

    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be blank")
    private String password;

    @NotNull(message = "Repeated password cannot be null")
    @NotBlank(message = "Repeated password cannot be blank")
    private String repeatedPassword;

    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be blank")
    private String name;

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Surname cannot be blank")
    private String surname;

}
