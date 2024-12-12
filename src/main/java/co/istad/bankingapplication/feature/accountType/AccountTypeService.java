package co.istad.bankingapplication.feature.accountType;


import co.istad.bankingapplication.feature.accountType.dto.AccountTypeCreateRequest;
import co.istad.bankingapplication.feature.accountType.dto.AccountTypeResponse;
import co.istad.bankingapplication.feature.accountType.dto.AccountTypeUpdateRequest;

import java.util.List;

public interface AccountTypeService {
    /**
     * Find All AccountTypes
     * @return {@link List<AccountTypeResponse>}
     */
    List<AccountTypeResponse> findAllAccountTypes();

    /**
     * Create new Account Type
     * @param accountTypeCreateRequest {@link AccountTypeCreateRequest}
     * @return {@link AccountTypeResponse}
     */
    AccountTypeResponse createAccountType(AccountTypeCreateRequest accountTypeCreateRequest);

    /**
     * update Account TypeByAlias
     * @param alias
     * @param accountTypeUpdateRequest {@link AccountTypeUpdateRequest}
     * @return {@link AccountTypeResponse}
     */
    AccountTypeResponse updateAccountTypeByAlias(String alias, AccountTypeUpdateRequest accountTypeUpdateRequest);

    /**
     * delete Account Type By Alias
     * @param alias {@link String alias}
     */
    void deleteAccountTypeByAlias(String alias);

    /**
     * get Account Type By Alias
     * @param alias {@link String}
     * @return {@link AccountTypeResponse}
     */
    AccountTypeResponse getAccountTypeByAlias(String alias);
}
