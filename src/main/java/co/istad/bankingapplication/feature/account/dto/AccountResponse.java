package co.istad.bankingapplication.feature.account.dto;

import co.istad.bankingapplication.feature.accountType.dto.AccountTypeResponse;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AccountResponse(
        String alias,
        String actName,
        String actNo,
        BigDecimal balance,
        Boolean isHidden,
        Boolean isDeleted,
        AccountTypeResponse accountType
) {
}
