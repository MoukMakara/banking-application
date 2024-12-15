package co.istad.bankingapplication.feature.auth;

import co.istad.bankingapplication.feature.auth.dto.RegisterRequest;
import co.istad.bankingapplication.feature.auth.dto.RegisterResponse;
import co.istad.bankingapplication.feature.auth.dto.VerificationRequest;
import jakarta.mail.MessagingException;

public interface AuthService {
    RegisterResponse register(RegisterRequest registerRequest);
    void sendVerification(String email) throws MessagingException;
    void resendVerification(String email) throws MessagingException;
    void verify(VerificationRequest verificationRequest);
}
