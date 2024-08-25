package com.keehwan.api.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record LoginRequest(
        @NotBlank(message = "request.valid.email.required")
        @Email(message = "request.valid.invalid.email")
        String email,

       @NotBlank(message = "request.valid.password.required")
       @Length(min = 8, max = 64)
       String password) {}
