package co.istad.bankingapplication.feature.account.dto;

import jakarta.validation.constraints.NotBlank;

public record AccountRenameRequest(
        @NotBlank(message = "Alias is require")
        String alias
) {
}
