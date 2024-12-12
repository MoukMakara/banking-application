package co.istad.bankingapplication.feature.account;

import co.istad.bankingapplication.feature.account.dto.*;
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

    /**
     * update Status Account Soft delete
     * @param alias
     * @param accountUpdateStatusRequest
     * @return {@link AccountResponse}
     */
    AccountResponse updateStatusAccount(String alias, AccountUpdateStatusRequest accountUpdateStatusRequest);

    /**
     * delete Account By Account Number Hard delete
     * @param actNo
     */
    void deleteAccountByActNo(String actNo);

    /**
     * update Account By Account Number
     * @param actNo
     * @param accountUpdateRequest
     * @return {@link AccountResponse}
     */
    AccountResponse updateAccountByActNo(String actNo, AccountUpdateRequest accountUpdateRequest);
}
