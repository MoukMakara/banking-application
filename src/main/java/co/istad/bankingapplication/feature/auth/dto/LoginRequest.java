package co.istad.bankingapplication.feature.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "phoneNumber is require")
        String phoneNumber,

        @NotBlank(message = "password is require")
        String password
) {
}
