package co.istad.bankingapplication.feature.account;

import co.istad.bankingapplication.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    // SELECT * accounts FROM WHERE actNo = ?
    boolean existsByActNo(String actNo);

    boolean existsByActName(String actName);

    boolean existsByAlias(String alias);

    Optional<Account> findByActNo(String actNo);
}
