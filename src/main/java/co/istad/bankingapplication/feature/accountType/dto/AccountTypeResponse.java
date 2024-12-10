package co.istad.bankingapplication.feature.accountType.dto;

public record AccountTypeResponse(
        String alias,
        String name,
        String description,
        Boolean isDeleted
) {
}
