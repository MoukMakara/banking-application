package co.istad.bankingapplication.mapper;

import co.istad.bankingapplication.domain.Account;
import co.istad.bankingapplication.feature.account.dto.AccountCreateRequest;
import co.istad.bankingapplication.feature.account.dto.AccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    // map Account to AccountResponse
    // source = Account
    // target = AccountResponse
//    @Mapping(source = "accountType.alias", target = "accountTypeAlias")
    AccountResponse toAccountResponse(Account account);

    // map DTO to domain model
    // source = AccountCreateRequest
    // target = Account
    Account fromAccountCreateRequest(AccountCreateRequest accountCreateRequest);
}
