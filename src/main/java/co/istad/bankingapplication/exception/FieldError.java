package co.istad.bankingapplication.exception;

import lombok.Builder;

@Builder
public record FieldError(
        String field,
        String detail
) {
}
