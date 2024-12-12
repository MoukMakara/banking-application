package co.istad.bankingapplication.feature.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AccountUpdateStatusRequest(
        @NotNull(message = "isDeleted is require")
        Boolean isDeleted
) {
}
