package co.istad.bankingapplication.feature.user;

import co.istad.bankingapplication.domain.User;
import co.istad.bankingapplication.domain.UserSendVerification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserVerificationRepository extends JpaRepository<UserSendVerification, Integer> {
}
