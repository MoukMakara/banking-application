package co.istad.bankingapplication.feature.account.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AccountTransferLimitRequest(
        @NotNull(message = "amount is require")
                @Min(1000)
        BigDecimal amount
) {
}
