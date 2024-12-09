package co.istad.bankingapplication.feature.account;

import co.istad.bankingapplication.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    // SELECT * accounts FROM WHERE actNo = ?
    boolean existsByActNo(String actNo);

    boolean existsByActName(String actName);

    boolean existsByAlias(String alias);
}
