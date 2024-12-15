package co.istad.bankingapplication.feature.user;

import co.istad.bankingapplication.domain.User;
import co.istad.bankingapplication.domain.UserVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserVerificationRepository extends JpaRepository<UserVerification, Integer> {
    Optional<UserVerification> findByUserAndAndVerificationCode(User user, String verificationCode);
    Optional<UserVerification> findByUser(User user);
}
