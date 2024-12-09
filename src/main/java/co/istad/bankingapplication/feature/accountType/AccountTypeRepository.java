package co.istad.bankingapplication.feature.accountType;

import co.istad.bankingapplication.domain.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountTypeRepository extends JpaRepository<AccountType, Integer> {
    /**
     * SELECT * FROM WHERE alias = ?
     * @param alias {@link String alias}
     * @return {@link Optional}
     */
    Optional<AccountType> findByAlias(String alias);
}
