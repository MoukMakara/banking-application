package co.istad.bankingapplication.feature.user;

import co.istad.bankingapplication.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * SELECT * FROM WHERE uuid = ?
     * @param uuid {@link String uuid}
     * @return {@link Optional}
     */
    Optional<User> findByUuid(String uuid);
}
