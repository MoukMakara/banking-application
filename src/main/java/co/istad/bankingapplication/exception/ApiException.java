package co.istad.bankingapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleValidationError(MethodArgumentNotValidException ex) {
        // Map the field errors from FieldError to custom FieldError
        List<FieldError> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> FieldError.builder()
                        .field(err.getField()) // Spring's FieldError method
                        .detail(err.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());
        // Create the ErrorResponse with the list of custom FieldError instances
        ErrorResponse<List<FieldError>> errorResponse = new ErrorResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                errors
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleResponseStatusException(ResponseStatusException e) {
        System.out.println("ResponseStatusException: " + e.getMessage());
        ErrorResponse<String> errorResponse = ErrorResponse.<String>builder()
                .code(e.getStatusCode().value())
                .reason(e.getReason())
                .build();
        return ResponseEntity
                .status(e.getStatusCode())
                .body(Map.of("error", errorResponse));
    }
}
