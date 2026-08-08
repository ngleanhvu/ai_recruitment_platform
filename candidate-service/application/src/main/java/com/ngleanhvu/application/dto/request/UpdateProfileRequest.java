package com.ngleanhvu.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateProfileRequest (
        @NotBlank(message = "First name must not be empty") String firstName,
        @NotBlank(message = "Last name must not be empty") String lastName,
        @Email(message = "Email is invalid") @NotBlank(message = "Email must not be empty")
        String email,
        String phone,
        String summary
) {
}
