package com.mikolajjanik.hospital_catering_admin.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class HospitalDTO {

    @NotBlank(message = "Name is required.")
    private String name;

    @NotBlank(message = "Phone number is required.")
    private String phoneNumber;

    @NotBlank(message = "Street is required.")
    private String street;

    @NotNull
    @Min(1)
    private int buildingNo;

    @Pattern(regexp = "[0-9]{2}-[0-9]{3}")
    private String zipCode;

}
