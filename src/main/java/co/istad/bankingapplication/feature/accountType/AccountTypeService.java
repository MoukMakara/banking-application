package co.istad.bankingapplication.feature.accountType;


import co.istad.bankingapplication.feature.accountType.dto.AccountTypeCreateRequest;
import co.istad.bankingapplication.feature.accountType.dto.AccountTypeResponse;

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
}
