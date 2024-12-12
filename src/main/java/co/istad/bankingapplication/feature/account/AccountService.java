package co.istad.bankingapplication.feature.account;

import co.istad.bankingapplication.feature.account.dto.AccountCreateRequest;
import co.istad.bankingapplication.feature.account.dto.AccountResponse;
import org.springframework.data.domain.Page;

public interface AccountService {
    /**
     * create new Account
     * @param accountCreateRequest {@link AccountCreateRequest}
     * @return {@link AccountResponse}
     */
    AccountResponse createAccount(AccountCreateRequest accountCreateRequest);

    /**
     * find All Accounts by Pagination
     * @param pageNumber
     * @param pageSize
     * @return {@link Page<AccountResponse>}
     */
    Page<AccountResponse> findAllAccounts(int pageNumber, int pageSize);

    /**
     * find Account By ActNo
     * @param actNo
     * @return {@link AccountResponse}
     */
    AccountResponse findAccountByActNo(String actNo);
}
