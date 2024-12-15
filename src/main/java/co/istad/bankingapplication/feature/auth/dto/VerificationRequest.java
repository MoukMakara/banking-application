package co.istad.bankingapplication.feature.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record VerificationRequest(
        @NotBlank(message = "email is require")
        String email,
        @NotBlank(message = "verificationCode is require")
        String verificationCode
) {
}
