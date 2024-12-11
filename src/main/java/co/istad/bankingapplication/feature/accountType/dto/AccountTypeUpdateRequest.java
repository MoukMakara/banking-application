package co.istad.bankingapplication.feature.accountType.dto;

public record AccountTypeUpdateRequest(
        String description,
        Boolean isDeleted
) {
}
