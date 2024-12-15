package co.istad.bankingapplication.feature.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record ChangPasswordRequest(
        @NotBlank(message = "oldPassword is require")
        String oldPassword,

        @NotBlank(message = "password is require")
        String password,

        @NotBlank(message = "confirmPassword is require")
        String confirmPassword
) {
}
