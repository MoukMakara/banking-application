package co.istad.bankingapplication.feature.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AccountCreateRequest(
        String alias,

        @NotBlank(message = "actNo is require")
        String actNo,

        @NotNull(message = "balance is require")
        BigDecimal balance,

        @NotBlank(message = "accountTypeAlias is require")
        String accountTypeAlias,

        @NotBlank(message = "userUuid is require")
        String userUuid
) {
}
