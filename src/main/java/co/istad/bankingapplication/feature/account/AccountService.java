package co.istad.bankingapplication.feature.account;

import co.istad.bankingapplication.feature.account.dto.AccountCreateRequest;
import co.istad.bankingapplication.feature.account.dto.AccountRenameRequest;
import co.istad.bankingapplication.feature.account.dto.AccountResponse;
import co.istad.bankingapplication.feature.account.dto.AccountTransferLimitRequest;
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

    /**
     * rename Account By Account Number
     * @param actNo
     * @param accountRenameRequest
     * @return {@link AccountResponse}
     */
    AccountResponse renameAccountByActNo(String actNo, AccountRenameRequest accountRenameRequest);

    /**
     * hide Account By Account Number
     * @param actNo
     */
    void hideAccountByActNo(String actNo);

    /**
     * update TransferLimit By Account Number
     * @param actNo
     * @param accountTransferLimitRequest
     */
    void updateTransferLimitByActNo(String actNo, AccountTransferLimitRequest accountTransferLimitRequest);
}
