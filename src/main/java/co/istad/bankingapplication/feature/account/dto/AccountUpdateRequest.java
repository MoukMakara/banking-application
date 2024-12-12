package co.istad.bankingapplication.feature.account.dto;

import co.istad.bankingapplication.feature.accountType.dto.AccountTypeResponse;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AccountUpdateRequest(

        String alias,
        String actName,
        String actNo
) {
}
