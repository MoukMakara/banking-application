package co.istad.bankingapplication.feature.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record SendVerificationRequest(
        @NotBlank(message = "email is require")
        String email
) {
}
