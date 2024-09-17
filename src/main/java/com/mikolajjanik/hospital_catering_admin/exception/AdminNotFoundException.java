package com.mikolajjanik.hospital_catering_admin.exception;

import java.io.IOException;

public class AdminNotFoundException extends IOException {
    public AdminNotFoundException(String email) {
        super("User with email " + email + " does not exist.");
    }
}
