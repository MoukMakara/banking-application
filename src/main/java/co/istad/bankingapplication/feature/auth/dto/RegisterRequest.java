package co.istad.bankingapplication.feature.auth.dto;

import jakarta.validation.constraints.*;

public record RegisterRequest(
        @NotBlank(message = "phoneNumber is require")
                @Size(min = 9, max = 10, message = "phoneNumber must 9 or 10 digits")
        String phoneNumber,

        @NotBlank(message = "pin is require")
                @Size(min = 4, max = 4, message = "pin must be 4 digits")
        String pin,

        @NotBlank(message = "password is require")
                @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
                        message = "password must be Has minimum 8 characters in length" +
                                        "At least one uppercase English letter" +
                                        "At least one lowercase English letter" +
                                        "At least one digit" +
                                        "At least one special character")
        String password,

        @NotBlank(message = "confirmPassword is require")
        @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
                message = "password must be Has minimum 8 characters in length" +
                        "At least one uppercase English letter" +
                        "At least one lowercase English letter" +
                        "At least one digit" +
                        "At least one special character")
        String confirmPassword,

        @NotBlank(message = "nationalCardId is require")
        String nationalCardId,

        @NotBlank(message = "name is require")
        String name,

        @NotBlank(message = "email is require")
        String email,

        @NotBlank(message = "gender is require")
        String gender,

        @NotNull(message = "acceptTerm is require")
        Boolean acceptTerm
) {
}
