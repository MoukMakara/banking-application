package co.istad.bankingapplication.feature.accountType.dto;

import jakarta.validation.constraints.NotBlank;

public record AccountTypeCreateRequest (

        @NotBlank(message = "alias is require")
        String alias,

        @NotBlank(message = "name is require")
        String name,

        String description
) {
}
