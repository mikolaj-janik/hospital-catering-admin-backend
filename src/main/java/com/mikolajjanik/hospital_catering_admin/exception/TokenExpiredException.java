package com.mikolajjanik.hospital_catering_admin.exception;

import java.io.IOException;

public class TokenExpiredException extends IOException {
    public TokenExpiredException() {
        super("The token has expired. ");
    }
}
