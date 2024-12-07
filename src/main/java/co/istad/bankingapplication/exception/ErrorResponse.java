package co.istad.bankingapplication.exception;

import lombok.*;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class ErrorResponse<T> {
    private Integer code;
    private T reason;
}
